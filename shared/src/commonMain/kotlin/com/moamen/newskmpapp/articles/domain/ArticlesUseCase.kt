package com.moamen.newskmpapp.articles.domain

class ArticlesUseCase(private val repository: ArticlesRepository) {

    suspend fun getArticles(
        forceFetch: Boolean,
        country: String,
        category: String
    ): List<Article> { // suspend == async let in swift
        return repository.getArticles(forceFetch, country, category)
    }
}
