package com.moamen.newskmpapp.db
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import moamen.newskmpapp.db.NewsKMPAppDatabase

actual class DatabaseDriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = NewsKMPAppDatabase.Schema,
            context = context,
            name = "NewsKMPApp.Database.db"
        )
}