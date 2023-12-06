package com.egample.kmmdemoapp.cache

import com.squareup.sqldelight.Query


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeHistory()
        }
    }

    internal fun getHistory(): Query<String> {
        return dbQuery.getUserHistory()
    }

    internal fun insertHistory(name: String) {
        dbQuery.insertUserName(name)
    }
}