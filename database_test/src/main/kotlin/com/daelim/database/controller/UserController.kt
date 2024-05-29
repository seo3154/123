package com.daelim.database.controller

import com.daelim.database.core.dto.UserDto
import com.daelim.database.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @PostMapping("/register")
    fun register(
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam address: String // 주소 매개변수 추가
    ): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.registerUser(username, password, address))
    }

    @GetMapping("/login")
    fun login(
        @RequestParam username: String,
        @RequestParam password: String,
    ): ResponseEntity<String> {
        return if (userService.validateUser(username, password)) {
            ResponseEntity.ok(userService.createSession(username))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/check")
    fun check(
        @RequestParam username: String,
        @RequestParam sessionId: String,
    ): ResponseEntity<String> {
        return if (userService.checkSession(username, sessionId)) {
            ResponseEntity.ok("Session valid")
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
