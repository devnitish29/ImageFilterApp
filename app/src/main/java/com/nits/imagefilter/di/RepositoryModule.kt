package com.nits.imagefilter.di

import com.nits.imagefilter.repository.EditImageRepository
import com.nits.imagefilter.repository.EditImageRepositoryImpl
import com.nits.imagefilter.repository.SavedImagesRepository
import com.nits.imagefilter.repository.SavedImagesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
    factory<SavedImagesRepository> { SavedImagesRepositoryImpl(androidContext()) }
}