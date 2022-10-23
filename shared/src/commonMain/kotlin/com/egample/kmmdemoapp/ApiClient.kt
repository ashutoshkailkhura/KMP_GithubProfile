package com.egample.kmmdemoapp

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class UserInfo(
    val id: Int,
    val avatar_url: String,
    val followers_url: String,
    val repos_url: String,
    val name: String = "Ashutosh Kailkhura",
    val location: String = "Dehradun, Uttarakhand",
    val email: String? = null,
    val bio: String = "Being Awesome",
    val public_repos: Int = 11,
    val public_gists: Int = 2,
    val followers: Int = 2,
    val following: Int = 12,
    val created_at: String = "2016-09-10T12:30:09Z",
)

class ApiClient {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getUserInfo(): UserInfo {
        return client
            .get("https://api.github.com/users/ashutoshkailkhura")
            .body()

    }

}