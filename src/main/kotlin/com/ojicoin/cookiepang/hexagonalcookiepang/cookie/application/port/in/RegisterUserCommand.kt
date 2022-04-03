package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`

data class RegisterUserCommand(
    val nickName: String,
    val introduction: String?,
    val walletAddress: String,
)
