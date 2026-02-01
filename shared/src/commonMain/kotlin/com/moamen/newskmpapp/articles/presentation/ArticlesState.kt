package com.moamen.newskmpapp.articles.presentation

import com.moamen.newskmpapp.articles.domain.Article

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)