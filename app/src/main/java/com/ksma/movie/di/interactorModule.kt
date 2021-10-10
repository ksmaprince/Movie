package com.ksma.movie.di

import com.ksma.domain.interactor.MovieListInteractor
import org.koin.dsl.module.module

val interactorModule = module {

    factory { MovieListInteractor(get(), get(), get()) }

}