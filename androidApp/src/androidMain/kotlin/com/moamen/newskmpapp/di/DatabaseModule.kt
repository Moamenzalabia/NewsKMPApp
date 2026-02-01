package com.moamen.newskmpapp.di
import app.cash.sqldelight.db.SqlDriver
import com.moamen.newskmpapp.db.DatabaseDriverFactory
import moamen.newskmpapp.db.NewsKMPAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }

    single<NewsKMPAppDatabase> { NewsKMPAppDatabase(get()) }
}