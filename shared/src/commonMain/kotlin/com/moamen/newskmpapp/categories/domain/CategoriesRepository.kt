package com.moamen.newskmpapp.categories.domain

interface CategoriesRepository {
    suspend fun getCategories(forceFetch: Boolean): List<Category>
}
