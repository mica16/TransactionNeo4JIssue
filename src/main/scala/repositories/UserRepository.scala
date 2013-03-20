package repositories

import org.springframework.data.neo4j.repository.GraphRepository
import models.User

trait UserRepository extends GraphRepository[User] {

  def getUserByEmail(email: String): User

}
