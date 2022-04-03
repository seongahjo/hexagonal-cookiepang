package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.adapter.out.persistence

import org.springframework.data.repository.CrudRepository

interface SpringDataUserRepository : CrudRepository<UserJdbcEntity, Long> {
    fun findByWalletAddress(walletAddress: String): UserJdbcEntity?
}
