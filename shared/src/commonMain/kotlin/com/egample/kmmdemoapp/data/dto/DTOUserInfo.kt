package com.egample.kmmdemoapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DTOUserInfo(
    @SerialName("id")
    val id: Int,
    @SerialName("avatar_url")
    val avatar_url: String,
    @SerialName("followers_url")
    val followers_url: String,
    @SerialName("repos_url")
    val repos_url: String,
    @SerialName("name")
    val name: String = "Ashutosh Kailkhura",
    @SerialName("location")
    val location: String = "Dehradun, Uttarakhand",
    @SerialName("email")
    val email: String? = null,
    @SerialName("bio")
    val bio: String = "Being Awesome",
    @SerialName("public_repos")
    val public_repos: Int = 11,
    @SerialName("public_gists")
    val public_gists: Int = 2,
    @SerialName("followers")
    val followers: Int = 2,
    @SerialName("following")
    val following: Int = 12,
    @SerialName("created_at")
    val created_at: String = "2016-09-10T12:30:09Z",
)