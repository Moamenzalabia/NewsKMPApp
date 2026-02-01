package com.moamen.newskmpapp.articles.data

import moamen.newskmpapp.db.NewsKMPAppDatabase

class ArticlesDataSource(private val database: NewsKMPAppDatabase) {

    fun getAllArticles(): List<ArticleRaw> =
        database.newsKMPAppQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        database.newsKMPAppQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    fun clearArticles() =
        database.newsKMPAppQueries.removeAllArticles()

    private fun insertArticle(articleRaw: ArticleRaw) {
        database.newsKMPAppQueries.insertArticle(
            articleRaw.title,
            articleRaw.date,
            articleRaw.description,
            articleRaw.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        date: String,
        description: String?,
        url: String?
    ): ArticleRaw =
        ArticleRaw(
            title,
            description,
            date,
            url
        )
}
