package com.moamen.newskmpapp.articles.di
import com.moamen.newskmpapp.articles.data.ArticlesDataSource
import com.moamen.newskmpapp.articles.data.ArticlesRepositoryImplementation
import com.moamen.newskmpapp.articles.data.ArticlesService
import com.moamen.newskmpapp.articles.domain.ArticlesRepository
import com.moamen.newskmpapp.articles.domain.ArticlesUseCase
import com.moamen.newskmpapp.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepositoryImplementation(get(), get()) }
}
