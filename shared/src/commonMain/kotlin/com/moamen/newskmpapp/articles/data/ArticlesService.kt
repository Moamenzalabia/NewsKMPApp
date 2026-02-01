package com.moamen.newskmpapp.articles.data
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

import com.moamen.newskmpapp.core.Constants

class ArticlesService(private val httpClient: HttpClient) {

    suspend fun fetchArticles(
        country: String,
        category: String,
    ): List<ArticleRaw> {
        val response: ArticlesResponse = httpClient.get("${Constants.BASE_URL}top-headlines?country=$country&category=$category&apiKey=${Constants.API_KEY}").body()
        return response.articles
    }
}