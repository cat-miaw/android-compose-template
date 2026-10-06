package com.example.aplikasi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi.di.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * One immutable state object per screen, exposed as a StateFlow. Composable
 * functions read it with `collectAsStateWithLifecycle`, which keeps collection
 * off the main thread while stopped and avoids recomposition when nothing changed.
 */
data class HomeUiState(
    val isLoading: Boolean = true,
    val items: List<String> = emptyList(),
    val error: String? = null,
)

class HomeViewModel(
    private val repository: HomeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            runCatching { repository.loadItems() }
                .onSuccess { items ->
                    _uiState.update { it.copy(isLoading = false, items = items) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, error = error.message ?: "Something went wrong")
                    }
                }
        }
    }
}