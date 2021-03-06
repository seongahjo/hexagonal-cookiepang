package com.ojicoin.cookiepang.hexagonalcookiepang.cookie.adapter.out.persistence

import com.ojicoin.cookiepang.hexagonalcookiepang.cookie.domain.UserStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.validation.constraints.Size

@Table("users")
class UserJdbcEntity(
    @Id @Column("user_id") var id: Long? = null,
    @Column("wallet_address") @field:Size(max = 255) val walletAddress: String,
    @Column("nickname") @field:Size(max = 100) var nickname: String,
    @Column("introduction") @field:Size(max = 255) var introduction: String?,
    @Column("profile_url") @field:Size(max = 255) var profileUrl: String?,
    @Column("background_url") @field:Size(max = 255) var backgroundUrl: String?,
    @Column("status") var status: UserStatus, // TODO : enum을 공유할까?
    @Column("finish_onboard") var finishOnboard: Boolean,
)
