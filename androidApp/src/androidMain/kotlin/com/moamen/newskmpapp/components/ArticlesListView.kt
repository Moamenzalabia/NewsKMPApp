package com.moamen.newskmpapp.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moamen.newskmpapp.articles.domain.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesListView(
    articles: List<Article>,
    isLoading: Boolean,
    onRefresh: () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    Box(modifier = Modifier.fillMaxSize()) {
        PullToRefreshBox(
            state = pullToRefreshState,
            isRefreshing = isLoading,
            onRefresh = onRefresh
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(articles) { article ->
                    ArticleItemView(article = article)
                }
            }
        }
    }
}
