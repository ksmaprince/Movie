package com.ksma.movie.feature.ui.moviedetail

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.ksma.domain.interactor.MovieListInteractor
import com.ksma.domain.viewstate.MovieListViewState
import io.reactivex.Observable

class MovieDetailPresenter(private val interactor: MovieListInteractor) :
    MviBasePresenter<MovieDetailView, MovieListViewState>() {

    override fun bindIntents() {

        val favouriteMovieIntent: Observable<MovieListViewState> =
            intent(MovieDetailView::FavouriteMovieIntent)
                .flatMap {
                    interactor.executeFavouriteMovie(it)
                }


        val allIntents = Observable.mergeArray(
            favouriteMovieIntent
        )

        subscribeViewState(allIntents, MovieDetailView::render)
    }
}