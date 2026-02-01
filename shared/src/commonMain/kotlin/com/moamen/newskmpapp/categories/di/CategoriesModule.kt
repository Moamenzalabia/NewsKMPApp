package com.moamen.newskmpapp.categories.di
import com.moamen.newskmpapp.categories.data.CategoriesDataSource
import com.moamen.newskmpapp.categories.data.CategoriesRepositoryImplementation
import com.moamen.newskmpapp.categories.data.CategoriesService
import com.moamen.newskmpapp.categories.domain.CategoriesRepository
import com.moamen.newskmpapp.categories.domain.CategoriesUseCase
import com.moamen.newskmpapp.categories.presentation.CategoriesViewModel
import org.koin.dsl.module


val categoriesModule = module {

    single<CategoriesService> { CategoriesService(get()) }
    single<CategoriesUseCase> { CategoriesUseCase(get()) }
    single<CategoriesViewModel> { CategoriesViewModel(get()) }
    single<CategoriesDataSource> { CategoriesDataSource(get()) }
    single<CategoriesRepository> { CategoriesRepositoryImplementation(get(), get()) }
}
