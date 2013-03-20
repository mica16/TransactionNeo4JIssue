package models

import org.springframework.data.neo4j.annotation.{RelatedTo, GraphId, NodeEntity}
import utils.SpringDataShortAnnotations._
import java.lang.Long

@NodeEntity
class User (@Indexed val lastName: String, val firstName: String, @Indexed val email: String) {

  @GraphId private var oid: Long = _

  @RelatedTo(`type` = "PARTICIPATES_IN")
  val meetings: java.util.Set[Meeting] = new java.util.HashSet[Meeting]()

  private def this() {
    this("unknown", "unknown", "unknown") //dummy values in order to satisfy Scala's constructor syntax
  }

  def participateIn(meeting: Meeting) = {
    meeting.creator = this
    meetings.add(meeting)  //involve a NotInTransactionException !!
  }

  override def equals(other: Any): Boolean = {
    other match {
      case that: User => (that eq this) || ((that canEqual this) && that.oid != null && that.oid == this.oid)
      case _ => false
    }
  }

  def canEqual(other: Any) = other.isInstanceOf[User]

  override def hashCode(): Int = {
    if (oid == null) {
      System.identityHashCode(this)
    } else {
      oid.hashCode()
    }
  }

}

object User {

  def apply(lastName: String, firstName: String, email: String) = {
    new User(lastName, firstName, email)
  }

}
