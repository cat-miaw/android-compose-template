package com.example.template.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * The screen shows three mutually exclusive states, so it takes a single
 * immutable state object rather than several boolean flags. The compiler can
 * then skip it entirely when nothing relevant changed.
 */
@Composable
fun HomeScreen(
    state: HomeUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        when {
            state.isLoading -> LoadingContent(padding)

            state.error != null -> ErrorContent(message = state.error, onRetry = onRetry, padding = padding)

            else -> ItemList(items = state.items, padding = padding)
        }
    }
}

@Composable
private fun LoadingContent(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    padding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = onRetry) { Text("Retry: $message") }
    }
}

/**
 * LazyColumn composes only the visible rows plus a small prefetch buffer. The
 * `key` keeps item identity stable across reloads so Compose reuses the existing
 * nodes instead of rebuilding them — the difference is visible on long lists.
 */
@Composable
private fun ItemList(items: List<String>, padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = padding,
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        items(items = items, key = { it }) { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )
            HorizontalDivider()
        }
    }
}