package models

import org.springframework.data.neo4j.annotation.{RelatedTo, Fetch, GraphId, NodeEntity}
import utils.SpringDataShortAnnotations._
import java.lang.Long
import org.neo4j.graphdb.Direction

@NodeEntity
class Meeting private(@Indexed val title: String, val description: String) {

  @GraphId private var oid: Long = _

  @Fetch
  @RelatedTo(`type` = "PARTICIPATES_IN", direction = Direction.INCOMING)
  var creator: User = _

  private def this() {
    this("unknown", "unknown") //dummy values in order to satisfy Scala's constructor syntax
  }


  override def equals(other: Any): Boolean = {
    other match {
      case that: Meeting => (that eq this) || ((that canEqual this) && that.oid != null && that.oid == this.oid)
      case _ => false
    }
  }

  def canEqual(other: Any) = other.isInstanceOf[Meeting]

  override def hashCode(): Int = {
    if (oid == null) {
      System.identityHashCode(this)
    } else {
      oid.hashCode()
    }
  }

}

object Meeting {

  def apply(title: String, description: String) = new Meeting()

}