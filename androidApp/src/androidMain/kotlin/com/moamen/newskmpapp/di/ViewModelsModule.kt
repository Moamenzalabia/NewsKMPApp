
package com.moamen.newskmpapp.di
import com.moamen.newskmpapp.categories.presentation.CategoriesViewModel
import com.moamen.newskmpapp.articles.presentation.ArticlesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {

    // use get() to instruct coin to find the dependency from the dependency graph

    viewModelOf(::ArticlesViewModel)
    viewModelOf(::CategoriesViewModel)
}
