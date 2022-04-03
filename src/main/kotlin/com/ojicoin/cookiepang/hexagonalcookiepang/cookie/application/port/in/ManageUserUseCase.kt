package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.User

interface ManageUserUseCase {
    fun registerUser(registerUserCommand: RegisterUserCommand): User
}
