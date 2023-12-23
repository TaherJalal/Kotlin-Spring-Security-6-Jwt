package security.kotlinspringsecurity6.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import security.kotlinspringsecurity6.model.User
import java.util.UUID as UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findUserByEmail(email: String): User;
}