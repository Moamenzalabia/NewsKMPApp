package com.moamen.newskmpapp.di
import com.moamen.newskmpapp.categories.di.categoriesModule
import com.moamen.newskmpapp.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule,
    categoriesModule,
)