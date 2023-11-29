package com.egample.kmmdemoapp

import com.egample.kmmdemoapp.data.dto.DTOUserInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

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

    suspend fun getDetail(name: String): NetworkResult<DTOUserInfo> {
        return try {
            val result = client
                .get("https://api.github.com/users/${name}")
                .body<DTOUserInfo>()
            NetworkResult.Success(result)
        } catch (ex: Exception) {
            NetworkResult.Error(ex.message)
        }
    }

}