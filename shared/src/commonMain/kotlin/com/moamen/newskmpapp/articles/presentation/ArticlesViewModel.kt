package com.moamen.newskmpapp.articles.presentation
import com.moamen.newskmpapp.BaseViewModel
import com.moamen.newskmpapp.articles.domain.ArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {

    // MAR:- Public Properties
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    // MAR:- Private Properties
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = false))

    fun getArticles(
        forceFetch: Boolean = false,
        country: String,
        category: String
    ) {
        scope.launch {
            _articlesState.emit(
                ArticlesState(
                    loading = true,
                    articles = _articlesState.value.articles
                )
            )
            try {
                val fetchedArticles = useCase.getArticles(forceFetch, country, category)
                if (fetchedArticles.isEmpty()) {
                    _articlesState.emit(ArticlesState(error = "No articles found"))
                } else {
                    _articlesState.emit(ArticlesState(fetchedArticles))
                }
            } catch (e: Exception) {
                _articlesState.emit(ArticlesState(error = e.message))
            }
        }
    }
}

/*
So we are going to use the reactive programming and streams in order to facilitate the communication
between the ViewModel and the UI.
In coroutines, the streams are called flows.
So we have the Kotlin flow and the Kotlin state flow.
They are pretty much the same thing, with the only difference that the state flow is obligated to have an initial state.
*/
