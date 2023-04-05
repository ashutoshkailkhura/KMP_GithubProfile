package com.egample.kmmdemoapp.android.data

import com.egample.kmmdemoapp.data.dto.DTOUserInfo

fun DTOUserInfo.toUserDetail(): UserDetail {
    return UserDetail(
        name = this.name,
        img = this.avatar_url,
        id = this.id,
        location = this.location,
        email = this.email.orEmpty(),
        createdAt = this.created_at,
        bio = this.bio
    )
}