package com.ksma.movie.di

import com.ksma.data.datasource.local.CacheMovieDataSource
import org.koin.dsl.module.module

val cacheModule = module {

    single { CacheMovieDataSource(get()) }

}