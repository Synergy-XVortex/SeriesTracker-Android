package com.example.seriestracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seriestracker.ui.components.CarteSerie
import com.example.seriestracker.ui.viewmodel.PopularShowsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcranAccueil(
    viewModel: PopularShowsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Series Tracker", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->

        // Main container handling the 3 states
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                // STATE 1 : Loading
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }

                // STATE 2 : Error
                uiState.errorMessage != null -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Oups ! Une erreur est survenue :\n${uiState.errorMessage}",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(onClick = { viewModel.retry() }) {
                            Text("Réessayer")
                        }
                    }
                }

                // STATE 3 : Success (List of shows)
                uiState.tvShows.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp) // Space between cards
                    ) {
                        items(uiState.tvShows) { show ->
                            CarteSerie(tvShow = show)
                        }
                    }
                }

                // Default / Empty state
                else -> {
                    Text("Aucune série à afficher.")
                }
            }
        }
    }
}