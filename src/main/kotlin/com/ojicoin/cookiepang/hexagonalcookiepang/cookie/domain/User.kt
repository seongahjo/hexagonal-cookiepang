package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain

class User(
    val nickName: String,
    val introduction: String?,
    val profileImage: Image?,
    val backgroundImage: Image?,
    val wallet: Wallet,
    val status: UserStatus,
    val finishOnBoard: Boolean,
)

enum class UserStatus { ACTIVE }
