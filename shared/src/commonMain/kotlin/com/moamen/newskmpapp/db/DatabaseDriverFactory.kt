package com.moamen.newskmpapp.db
import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {

    fun createDriver(): SqlDriver
}