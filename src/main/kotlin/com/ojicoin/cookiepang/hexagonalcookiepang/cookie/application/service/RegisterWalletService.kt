package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.service

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`.RegisterWalletPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.Wallet
import org.springframework.stereotype.Service

@Service
class RegisterWalletService : RegisterWalletPort {
    override fun registerWallet(walletAddress: String): Wallet = Wallet(walletAddress = walletAddress)
}
