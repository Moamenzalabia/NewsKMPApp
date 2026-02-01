package com.moamen.newskmpapp.db
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import moamen.newskmpapp.db.NewsKMPAppDatabase

actual class DatabaseDriverFactory() {

    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = NewsKMPAppDatabase.Schema,
            name = "NewsKMPAppDatabase.db"
        )
}