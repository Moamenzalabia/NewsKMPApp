package com.moamen.newskmpapp.di
import com.moamen.newskmpapp.categories.presentation.CategoriesViewModel
import com.moamen.newskmpapp.articles.presentation.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {

    val modules = sharedKoinModules+ databaseModule

    startKoin {
        modules(modules)
    }
}

class ArticlesInjector : KoinComponent {

    val articlesViewModel: ArticlesViewModel by inject()
}

class CategoriesInjector : KoinComponent {

    val categoriesViewModel: CategoriesViewModel by inject()
}