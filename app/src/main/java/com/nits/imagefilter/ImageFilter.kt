package com.nits.imagefilter

import android.app.Application
import com.nits.imagefilter.di.repositoryModule
import com.nits.imagefilter.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class ImageFilter : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ImageFilter)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}