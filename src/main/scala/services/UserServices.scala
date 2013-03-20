package services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import models.User
import org.springframework.beans.factory.annotation.Autowired
import repositories.UserRepository

@Service
class UserServices {

  @Autowired
  var userRepository: UserRepository = _

  @Transactional(readOnly = true)
  def findByEmail(email: String) : User = {
    userRepository.getUserByEmail(email)
  }

  @Transactional
  def save(user: User): User = {
    userRepository.save(user)
  }
}
