package com.ksma.domain.viewstate

import com.ksma.domain.model.MovieInfo

sealed class MovieListViewState {

    class Error(val t: Throwable) : MovieListViewState()

    object Progress : MovieListViewState()

    class MovieList(val movies: List<MovieInfo>) : MovieListViewState()

}