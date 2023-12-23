package security.kotlinspringsecurity6.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import security.kotlinspringsecurity6.dto.AuthRegisterDTO
import security.kotlinspringsecurity6.service.AuthService

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(
        @RequestParam email: String,
        @RequestParam password: String
    ): String {
        return authService.register(email, password)
    }

    @PostMapping("/login")
    fun login(
        @RequestParam email: String,
        @RequestParam password: String
    ): String {
        return authService.login(email, password)
    }
}