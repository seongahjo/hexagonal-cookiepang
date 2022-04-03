package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.Wallet

interface LoadWalletPort {
    fun loadWallet(walletAddress: String): Wallet?
}
