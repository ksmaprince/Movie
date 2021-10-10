package com.ksma.movie.di

import com.ksma.data.datasource.remote.NetworkMovieDataSource
import org.koin.dsl.module.module

val networkModule = module {

    single { NetworkMovieDataSource() }
}