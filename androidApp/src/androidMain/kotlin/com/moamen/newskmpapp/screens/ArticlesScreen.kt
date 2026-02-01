package com.moamen.newskmpapp.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.moamen.newskmpapp.articles.domain.Article
import com.moamen.newskmpapp.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.koinViewModel
import com.moamen.newskmpapp.categories.presentation.CategoriesViewModel
import com.moamen.newskmpapp.categories.domain.Category
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import com.moamen.newskmpapp.components.CategoryTab
import com.moamen.newskmpapp.components.Loader
import com.moamen.newskmpapp.components.ErrorMessage
import com.moamen.newskmpapp.components.ArticlesListView

@Composable
fun ArticlesScreen(
    onAboutButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = koinViewModel(),
    categoriesViewModel: CategoriesViewModel = koinViewModel()
) {
    val articlesState = articlesViewModel.articlesState.collectAsState()
    val categoriesState by categoriesViewModel.categoriesState.collectAsState()
    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(categoriesState.categories) {
        if (categoriesState.categories.isNotEmpty()) {
            val category = categoriesState.categories.first()
            articlesViewModel.getArticles(
                forceFetch = false,
                country = category.country,
                category = category.category
            )
        }
    }

    Column {
        AppBar(onAboutButtonClick)
        
        if (categoriesState.categories.isNotEmpty()) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface,
                divider = {},
            ) {
                categoriesState.categories.forEachIndexed { index, category ->
                    CategoryTab(
                        category = category,
                        isSelected = selectedTabIndex == index,
                        onSelect = {
                            selectedTabIndex = index
                            articlesViewModel.getArticles(
                                forceFetch = true,
                                country = category.country,
                                category = category.category
                            )
                        }
                    )
                }
            }
        }

        when {
            articlesState.value.loading -> Loader()
            articlesState.value.error != null -> ErrorMessage(articlesState.value.error!!)
            articlesState.value.articles.isNotEmpty() -> {
                ArticlesListView(
                    articles = articlesState.value.articles,
                    isLoading = articlesState.value.loading,
                    onRefresh = {
                        if (categoriesState.categories.isNotEmpty()) {
                            val category = categoriesState.categories[selectedTabIndex]
                            articlesViewModel.getArticles(
                                forceFetch = true,
                                country = category.country,
                                category = category.category
                            )
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onAboutButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button",
                )
            }
        }
    )
}
