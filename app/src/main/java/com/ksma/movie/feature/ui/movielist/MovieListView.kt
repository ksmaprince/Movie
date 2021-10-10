package com.ksma.movie.feature.ui.movielist

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.ksma.domain.viewstate.MovieListViewState
import io.reactivex.Observable

interface MovieListView: MvpView{

    fun render(viewState: MovieListViewState)

    fun popularMovieListIntent(): Observable<Int>

    fun upComingMovieListIntent(): Observable<Int>
}