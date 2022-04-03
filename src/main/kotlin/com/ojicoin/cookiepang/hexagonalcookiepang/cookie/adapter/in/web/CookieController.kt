package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.adapter.`in`.web

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`.ManageUserUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CookieController(private val manageUserUseCase: ManageUserUseCase) {
    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    fun health() {
    }
}
