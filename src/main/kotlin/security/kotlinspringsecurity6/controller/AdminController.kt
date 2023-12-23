package security.kotlinspringsecurity6.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController {
    @GetMapping("/api/admin")
    fun test(): String{
        return "hello for admins"
    }
}