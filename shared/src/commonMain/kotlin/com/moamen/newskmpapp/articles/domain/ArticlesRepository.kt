package com.moamen.newskmpapp.articles.domain

interface ArticlesRepository {
    suspend fun getArticles(
        forceFetch: Boolean,
        country: String,
        category: String
    ): List<Article>
}
