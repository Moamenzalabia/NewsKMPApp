package com.moamen.newskmpapp.articles.data
import com.moamen.newskmpapp.articles.domain.Article
import com.moamen.newskmpapp.articles.domain.ArticlesRepository
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class ArticlesRepositoryImplementation(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) : ArticlesRepository {

    override suspend fun getArticles(
        forceFetch: Boolean,
        country: String,
        category: String
    ): List<Article> {
        if (forceFetch) {
            return fetchAndCacheArticles(country, category)
        }

        val articlesDb = dataSource.getAllArticles()
        println("Got ${articlesDb.size} from the database!")

        if (articlesDb.isEmpty()) {
            return fetchAndCacheArticles(country, category)
        }

        return mapArticles(articlesDb)
    }

    private suspend fun fetchAndCacheArticles(
        country: String,
        category: String
    ): List<Article> {
        val fetchedArticles = service.fetchArticles(country, category)
        dataSource.clearArticles()
        dataSource.insertArticles(fetchedArticles)
        return mapArticles(fetchedArticles)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> = articlesRaw.map { raw ->
        Article(
            raw.title,
            raw.description ?: "Click to find out more",
            getDaysAgoString(raw.date),
            raw.imageUrl
                ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
        )
    }

    @OptIn(ExperimentalTime::class)
    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.Companion.currentSystemDefault())
        val days = today.daysUntil(
            Instant.Companion.parse(date).toLocalDateTime(TimeZone.Companion.currentSystemDefault()).date
        )
        return when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }
    }
}
