package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.adapter.out.persistence

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out.LoadWalletPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out.PersistUserPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.User
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.Wallet
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(private val userRepository: SpringDataUserRepository) : PersistUserPort, LoadWalletPort {
    override fun persistUser(user: User) {
        userRepository.save(user.toJdbcEntity())
    }

    override fun loadWallet(walletAddress: String): Wallet? = userRepository.findByWalletAddress(walletAddress)?.let {
        return Wallet(walletAddress = it.walletAddress)
    }
}

fun User.toJdbcEntity() = UserJdbcEntity(
    walletAddress = wallet.walletAddress,
    nickname = nickName,
    introduction = introduction,
    profileUrl = profileImage?.url,
    backgroundUrl = backgroundImage?.url,
    status = status,
    finishOnboard = finishOnBoard,
)
