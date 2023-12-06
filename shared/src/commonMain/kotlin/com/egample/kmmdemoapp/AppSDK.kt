package com.egample.kmmdemoapp

import com.egample.kmmdemoapp.cache.Database
import com.egample.kmmdemoapp.cache.DatabaseDriverFactory

class AppSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)
    private val api = ApiClient()


    suspend fun getUserInfo(name: String) = api.getDetail(name)

    suspend fun getHistory() = database.getHistory()

    suspend fun saveInHistory(name: String) = database.insertHistory(name)

    suspend fun clearHistory() = database.clearDatabase()

}