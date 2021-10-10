package com.ksma.movie.di

import com.ksma.data.storage.InternalStorageManager
import org.koin.dsl.module.module

val internalStorageModule = module {
    single {
        InternalStorageManager(get())
    }
}