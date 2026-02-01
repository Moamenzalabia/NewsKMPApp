package com.moamen.newskmpapp.categories.domain

class CategoriesUseCase(private val repository: CategoriesRepository) {

    suspend fun getCategories(forceFetch: Boolean): List<Category> { // suspend == async let in swift
        return repository.getCategories(forceFetch)
    }
}
