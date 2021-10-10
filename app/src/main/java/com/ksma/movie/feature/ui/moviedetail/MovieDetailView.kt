package com.ksma.movie.feature.ui.moviedetail

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.ksma.domain.interactor.MovieListInteractor
import com.ksma.domain.model.MovieInfo
import com.ksma.domain.viewstate.MovieListViewState
import io.reactivex.Observable

interface MovieDetailView: MvpView{

    fun render(viewState: MovieListViewState)

    fun FavouriteMovieIntent(): Observable<Int>
}