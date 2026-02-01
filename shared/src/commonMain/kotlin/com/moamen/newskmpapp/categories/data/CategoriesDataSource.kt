package com.moamen.newskmpapp.categories.data
import moamen.newskmpapp.db.NewsKMPAppDatabase
import kotlin.String

class CategoriesDataSource(private val database: NewsKMPAppDatabase) {

    fun getAllCategories(): List<Source> =
        database.newsKMPAppQueries.selectAllCategories(::mapToSource).executeAsList()

    fun insertCategories(sources: List<Source>) {
        database.newsKMPAppQueries.transaction {
            sources.forEach { source ->
                insertCategory(source)
            }
        }
    }

    fun clearCategories() =
        database.newsKMPAppQueries.removeAllCategories()

    private fun insertCategory(source: Source) {
        database.newsKMPAppQueries.insertCategory(
            source.id,
            source.name,
            source.description,
            source.url,
            source.category,
            source.language,
            source.country
        )
    }

    private fun mapToSource(
        id: String,
        name: String,
        description: String,
        url: String,
        category: String,
        language: String,
        country: String
    ): Source =
        Source(
            id,
            name,
            description,
            url,
            category,
            language,
            country
        )
}