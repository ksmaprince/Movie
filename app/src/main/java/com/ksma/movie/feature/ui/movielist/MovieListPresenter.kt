package com.ksma.movie.feature.ui.movielist

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.ksma.domain.interactor.MovieListInteractor
import com.ksma.domain.viewstate.MovieListViewState
import io.reactivex.Observable

class MovieListPresenter(private val interactor: MovieListInteractor) :
    MviBasePresenter<MovieListView, MovieListViewState>() {

    override fun bindIntents() {

        val popularMovieIntent: Observable<MovieListViewState> =
            intent(MovieListView::popularMovieListIntent)
                .flatMap { interactor.executePopularMovie(it) }

        val upComingMovieIntent: Observable<MovieListViewState> =
            intent(MovieListView::upComingMovieListIntent)
                .flatMap { interactor.executeUpComingMovie(it) }

        val allIntents = Observable.mergeArray(
            popularMovieIntent,
            upComingMovieIntent
        )

        subscribeViewState(allIntents, MovieListView::render)
    }
}