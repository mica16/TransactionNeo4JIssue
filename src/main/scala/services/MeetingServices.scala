package services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import models.{Meeting, User}

@Service
class MeetingServices {

  @Transactional
  def save(meeting: Meeting, creator: User) {
    creator.participateIn(meeting) // this line leads to a NotInTransactionException (see User's participateIn method)
    meeting.creator = creator
    //no need to continue statements of save method here, for the issue demo
  }
}
