package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.Wallet

interface RegisterWalletPort {
    fun registerWallet(walletAddress: String): Wallet
}
