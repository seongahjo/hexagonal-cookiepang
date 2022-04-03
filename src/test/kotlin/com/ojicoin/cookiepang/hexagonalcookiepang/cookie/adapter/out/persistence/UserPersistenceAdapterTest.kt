package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.adapter.out.persistence

import com.navercorp.fixturemonkey.kotlin.KFixtureMonkeyBuilder
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.User
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserPersistenceAdapterTest(
    @Autowired private val sut: UserPersistenceAdapter,
    @Autowired private val userRepository: SpringDataUserRepository,
) {
    private val fixture = KFixtureMonkeyBuilder().build()

    @Test
    fun persistUser() {
        // given
        val expected = fixture.giveMeBuilder<User>()
            .setNull("id")
            .sample()

        // when
        sut.persistUser(expected)

        // then
        val actual = userRepository.findAll().first()
        then(actual.introduction).isEqualTo(expected.introduction)
        then(actual.nickname).isEqualTo(expected.nickName)
        then(actual.finishOnboard).isEqualTo(expected.finishOnBoard)
        then(actual.status).isEqualTo(expected.status)
        then(actual.profileUrl).isEqualTo(expected.profileImage?.url)
        then(actual.walletAddress).isEqualTo(expected.wallet.walletAddress)
        then(actual.backgroundUrl).isEqualTo(expected.backgroundImage?.url)
    }

    @Test
    fun loadWallet() {
        // given
        val expected = fixture.giveMeBuilder<User>()
            .setNull("id")
            .sample()
        userRepository.save(expected.toJdbcEntity())

        // when
        val actual = sut.loadWallet(expected.wallet.walletAddress)!!

        // then
        then(actual.walletAddress).isEqualTo(expected.wallet.walletAddress)
    }
}
