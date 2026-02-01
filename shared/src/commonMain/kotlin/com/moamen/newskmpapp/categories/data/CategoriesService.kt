package com.moamen.newskmpapp.categories.data
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

import com.moamen.newskmpapp.core.Constants

class CategoriesService(private val httpClient: HttpClient) {

    suspend fun fetchCategories(): List<Source> {
        val response: CategoriesResponse = httpClient.get("${Constants.BASE_URL}top-headlines/sources?apiKey=${Constants.API_KEY}").body()
        return response.sources
    }
}