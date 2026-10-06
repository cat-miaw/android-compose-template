package com.example.aplikasi

import android.app.Application
import android.content.Context
import com.example.aplikasi.di.AppContainer
import com.example.aplikasi.di.DefaultAppContainer

/**
 * Deliberately empty. Every dependency is created lazily by [AppContainer] on first
 * use, so nothing here adds work to cold start.
 *
 * If you later need startup work, keep it out of `onCreate` — App Startup or a
 * background coroutine on an idle dispatcher costs far less than blocking the
 * main thread before the first frame.
 */
class App : Application() {
    val container: AppContainer by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        DefaultAppContainer(this)
    }
}

/** Convenience accessor. Cheap: no work happens until a property is touched. */
val Context.appContainer: AppContainer
    get() = (applicationContext as App).container