package com.ksma.movie.di

import com.ksma.movie.feature.ui.moviedetail.MovieDetailPresenter
import com.ksma.movie.feature.ui.movielist.MovieListPresenter
import org.koin.dsl.module.module

val presenterModule = module {

    factory { MovieListPresenter(get()) }

    factory { MovieDetailPresenter(get()) }
}