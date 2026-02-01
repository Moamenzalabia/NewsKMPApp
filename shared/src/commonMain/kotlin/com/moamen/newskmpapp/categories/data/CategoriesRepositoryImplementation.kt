package com.moamen.newskmpapp.categories.data

import com.moamen.newskmpapp.categories.domain.CategoriesRepository
import com.moamen.newskmpapp.categories.domain.Category

class CategoriesRepositoryImplementation(
    private val dataSource: CategoriesDataSource,
    private val service: CategoriesService
) : CategoriesRepository {

    override suspend fun getCategories(forceFetch: Boolean): List<Category> {
        if (forceFetch) {
            return fetchAndCacheCategories()
        }

        val categoriesDb = dataSource.getAllCategories()
        println("Got ${categoriesDb.size} from the database!!")

        if (categoriesDb.isEmpty()) {
            return fetchAndCacheCategories()
        }

        return mapCategories(categoriesDb)
    }

    private suspend fun fetchAndCacheCategories(): List<Category> {
        val fetchedCategories = service.fetchCategories()
        dataSource.clearCategories()
        dataSource.insertCategories(fetchedCategories)
        return mapCategories(fetchedCategories)
    }

    private fun mapCategories(categories: List<Source>): List<Category> = categories.map { item ->
        Category(
            item.id,
            item.name,
            item.description,
            item.url,
            item.category,
            item.language,
            item.country
        )
    }
}
