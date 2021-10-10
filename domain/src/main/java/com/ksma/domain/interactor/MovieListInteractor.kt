package com.ksma.domain.interactor

import com.ksma.domain.executor.BackgroundThread
import com.ksma.domain.executor.PostExecutionThread
import com.ksma.domain.repository.MovieRepository
import com.ksma.domain.viewstate.MovieListViewState
import io.reactivex.Observable

class MovieListInteractor(
    private val movieRepository: MovieRepository,
    private val mainThread: PostExecutionThread,
    private val backgroundThread: BackgroundThread
) {

    fun executePopularMovie(page: Int?): Observable<MovieListViewState> {
        return movieRepository.getPopularMovieList(null)
            .map { MovieListViewState.MovieList(it) }
            .cast(MovieListViewState::class.java)
            .onErrorReturn { MovieListViewState.Error(it) }
            .startWith(MovieListViewState.Progress)
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }

    fun executeUpComingMovie(page: Int?): Observable<MovieListViewState> {
        return movieRepository.getUpcomingMovieList(null)
            .map { MovieListViewState.MovieList(it) }
            .cast(MovieListViewState::class.java)
            .onErrorReturn { MovieListViewState.Error(it) }
            .startWith(MovieListViewState.Progress)
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }

    fun executeFavouriteMovie(page: Int?): Observable<MovieListViewState> {
        return movieRepository.getAllFavouriteMovieList(null)
            .map { MovieListViewState.MovieList(it) }
            .cast(MovieListViewState::class.java)
            .onErrorReturn { MovieListViewState.Error(it) }
            .startWith(MovieListViewState.Progress)
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())

    }
}