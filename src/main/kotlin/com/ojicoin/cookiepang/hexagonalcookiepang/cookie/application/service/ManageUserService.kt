package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.service

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`.ManageUserUseCase
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`.RegisterUserCommand
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`.RegisterWalletPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out.LoadWalletPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out.PersistUserPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.User
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.UserStatus.ACTIVE
import org.springframework.stereotype.Service

@Service
class ManageUserService(
    private val persistUserPort: PersistUserPort,
    private val loadWalletPort: LoadWalletPort,
    private val registerWalletPort: RegisterWalletPort,
) : ManageUserUseCase {
    override fun registerUser(registerUserCommand: RegisterUserCommand): User {
        val wallet = loadWalletPort.loadWallet(registerUserCommand.walletAddress)

        if (wallet != null) {
            throw IllegalArgumentException("Wallet already exists. walletAddress: ${registerUserCommand.walletAddress}")
        }

        val user = User(
            nickName = registerUserCommand.nickName,
            introduction = registerUserCommand.introduction,
            profileImage = null,
            backgroundImage = null,
            wallet = registerWalletPort.registerWallet(walletAddress = registerUserCommand.walletAddress),
            status = ACTIVE,
            finishOnBoard = false
        )
        persistUserPort.persistUser(user)
        return user
    }
}
