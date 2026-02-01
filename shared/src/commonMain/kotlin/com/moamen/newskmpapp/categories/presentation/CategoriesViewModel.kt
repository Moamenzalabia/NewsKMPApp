package com.moamen.newskmpapp.categories.presentation
import com.moamen.newskmpapp.BaseViewModel
import com.moamen.newskmpapp.categories.domain.CategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val useCase: CategoriesUseCase
) : BaseViewModel() {

    // MAR:- Public Properties
    val categoriesState: StateFlow<CategoriesState> get() = _categoriesState

    // MAR:- Private Properties
    private val _categoriesState: MutableStateFlow<CategoriesState> =
        MutableStateFlow(CategoriesState(loading = true))

    init {
        getCategories()
    }

    fun getCategories(forceFetch: Boolean = false) {
        scope.launch {
            _categoriesState.emit(
                CategoriesState(
                    loading = true,
                    categories = _categoriesState.value.categories
                )
            )

            try {
                val fetchedCategories = useCase.getCategories(forceFetch)
                _categoriesState.emit(CategoriesState(fetchedCategories))
            } catch (e: Exception) {
                _categoriesState.emit(CategoriesState(error = e.message))
            }
        }
    }
}
