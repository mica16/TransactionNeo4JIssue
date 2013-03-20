import services.MeetingServices
import org.springframework.context.support.ClassPathXmlApplicationContext
import models.{User, Meeting}
import services.UserServices


object TransactionIssueDemo extends App {

  val context = new ClassPathXmlApplicationContext("application-context.xml")

  val meetingServices = context.getBean("meetingServices", classOf[MeetingServices])

  val userServices = context.getBean("userServices", classOf[UserServices])

  // works well since (I don't understand why...) all involved entities are newly created
  saveAMeetingWithANewlyCreatedUserEntity()


  //set a relationShip between a Meeting and a retrieved User from the graph
  //Doens't work !! => NotInTransactionException
  saveAMeetingWithARetrievedUserEntity()

  println("end")

  def saveAMeetingWithANewlyCreatedUserEntity() {
    val meeting = Meeting("myTitle", "myDescription")
    val user = User("myLastName", "myFistName", "test@test.com")
    meetingServices.save(meeting, user)
  }

  def saveAMeetingWithARetrievedUserEntity() {
    val meeting = Meeting("myTitle", "myDescription")
    userServices.save(User("myLastName", "myFistName", "test@test.com")) // save a User in the graph
    val retrievedUser = userServices.findByEmail("test@test.com")
    meetingServices.save(meeting, retrievedUser)
  }

}
