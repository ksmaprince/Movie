package com.ksma.movie.di

import com.ksma.data.repository.MovieRepositoryImpl
import com.ksma.domain.repository.MovieRepository
import org.koin.dsl.module.module

val repositoryModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get(), get()) }

}