package com.example.seriestracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriestracker.data.repository.TvShowRepository
import com.example.seriestracker.domain.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// 1. The State class representing the UI at any given moment
// It holds the list of shows, a loading flag, and an optional error message
data class PopularShowsUiState(
    val tvShows: List<TvShow> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

// 2. The ViewModel annotated for Hilt injection
@HiltViewModel
class PopularShowsViewModel @Inject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    // Internal mutable state flow
    private val _uiState = MutableStateFlow(PopularShowsUiState())
    // Public read-only state flow exposed to the UI
    val uiState: StateFlow<PopularShowsUiState> = _uiState.asStateFlow()

    // The init block is called immediately when the ViewModel is created
    init {
        fetchPopularShows()
    }

    // Function to fetch data using coroutines
    private fun fetchPopularShows() {
        viewModelScope.launch {
            // First, update the state to show the loading indicator
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            // Call the repository to get the result
            val result = repository.getPopularShows()

            // Handle success or failure using Kotlin's Result fold function
            result.fold(
                onSuccess = { shows ->
                    // Update state with the fetched list and stop loading
                    _uiState.update {
                        it.copy(
                            tvShows = shows,
                            isLoading = false
                        )
                    }
                },
                onFailure = { exception ->
                    // Update state with the error message and stop loading
                    _uiState.update {
                        it.copy(
                            errorMessage = exception.localizedMessage ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            )
        }
    }

    // Public function allowing the UI to retry fetching data if an error occurs
    fun retry() {
        fetchPopularShows()
    }
}