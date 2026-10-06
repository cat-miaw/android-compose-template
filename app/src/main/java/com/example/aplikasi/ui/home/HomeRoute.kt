package com.example.aplikasi.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Binds a [HomeViewModel] to [HomeScreen].
 *
 * `collectAsStateWithLifecycle` (not `collectAsState`) suspends collection while
 * the screen is not visible, so background work never recomposes a screen the
 * user cannot see.
 */
@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onRetry = viewModel::load,
        modifier = modifier,
    )
}