package security.kotlinspringsecurity6.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/api/user")
    fun test(): String{
        return "hello for users"
    }
}