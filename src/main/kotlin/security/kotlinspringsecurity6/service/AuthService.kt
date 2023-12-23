package security.kotlinspringsecurity6.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import security.kotlinspringsecurity6.model.User
import security.kotlinspringsecurity6.repository.UserRepository
import java.util.*

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JWTService,
    private val authManager: AuthenticationManager
) {

    fun register(
        email: String,
        password: String
    ): String {
        val user = User(UUID.randomUUID(),email, passwordEncoder.encode(password))
        userRepository.save(user)
        return "user added"
    }

    fun login(
        email: String,
        password: String
    ): String {
        authManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
        val user: User = userRepository.findUserByEmail(email)

        return jwtService.generateToken(email)
    }

}