package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.User

interface PersistUserPort {
    fun persistUser(user: User)
}
