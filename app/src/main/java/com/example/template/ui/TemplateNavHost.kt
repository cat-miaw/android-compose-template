package com.example.template.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.template.di.AppContainer
import com.example.template.ui.home.HomeRoute
import com.example.template.ui.home.HomeViewModel

private object Routes {
    const val HOME = "home"
}

@Composable
fun TemplateNavHost(
    container: AppContainer,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeRoute(
                // `viewModel` caches by store owner, so this does not rebuild the
                // ViewModel on every recomposition or config change.
                viewModel = viewModel<HomeViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T =
                            HomeViewModel(container.homeRepository) as T
                    },
                ),
            )
        }
    }
}