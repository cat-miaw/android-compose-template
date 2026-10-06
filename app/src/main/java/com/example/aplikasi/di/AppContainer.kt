package com.example.aplikasi.di

import android.content.Context

/**
 * Manual dependency container. Deliberately no Hilt/Koin: annotation processing
 * costs build time, and a reflection-based container costs startup time.
 *
 * Add a dependency here as a `by lazy` property. It is constructed on first
 * access, not on app start.
 */
interface AppContainer {
    val homeRepository: HomeRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val homeRepository: HomeRepository by lazy {
        HomeRepository(context)
    }
}