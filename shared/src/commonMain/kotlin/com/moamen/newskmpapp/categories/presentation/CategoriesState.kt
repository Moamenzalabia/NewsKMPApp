package com.moamen.newskmpapp.categories.presentation
import com.moamen.newskmpapp.categories.domain.Category

data class CategoriesState(
    val categories: List<Category> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)