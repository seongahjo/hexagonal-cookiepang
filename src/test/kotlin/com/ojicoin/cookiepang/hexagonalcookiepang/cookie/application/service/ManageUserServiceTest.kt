package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.service

import com.navercorp.fixturemonkey.kotlin.KFixtureMonkeyBuilder
import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`.RegisterUserCommand
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.`in`.RegisterWalletPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out.LoadWalletPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.application.port.out.PersistUserPort
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.Wallet
import org.assertj.core.api.BDDAssertions.then
import org.assertj.core.api.BDDAssertions.thenThrownBy
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

class ManageUserServiceTest {
    private val persistUserPort = mock<PersistUserPort>()
    private val loadWalletPort = mock<LoadWalletPort>()
    private val registerWalletPort = mock<RegisterWalletPort>()

    private val sut: ManageUserService = ManageUserService(
        persistUserPort = persistUserPort,
        loadWalletPort = loadWalletPort,
        registerWalletPort = registerWalletPort,
    )

    private val fixture = KFixtureMonkeyBuilder().build()

    @Test
    fun registerUser() {
        // given
        val registerUserCommand = fixture.giveMeOne<RegisterUserCommand>()
        given(registerWalletPort.registerWallet(registerUserCommand.walletAddress))
            .willReturn(Wallet(walletAddress = registerUserCommand.walletAddress))

        // when
        val actual = sut.registerUser(registerUserCommand)

        then(actual.nickName).isEqualTo(registerUserCommand.nickName)
        then(actual.wallet.walletAddress).isEqualTo(registerUserCommand.walletAddress)
        then(actual.introduction).isEqualTo(registerUserCommand.introduction)
    }

    @Test
    fun registerUserWhenWalletAddressExistsThrows() {
        // given
        val registerUserCommand = fixture.giveMeOne<RegisterUserCommand>()
        given(loadWalletPort.loadWallet(walletAddress = registerUserCommand.walletAddress))
            .willReturn(Wallet(walletAddress = registerUserCommand.walletAddress))

        // when, then
        thenThrownBy { sut.registerUser(registerUserCommand) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Wallet already exists.")
    }

    private inline fun <reified T> mock(): T = mock(T::class.java)
}
