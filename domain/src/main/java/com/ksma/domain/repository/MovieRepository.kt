package com.ksma.domain.repository

import com.ksma.domain.model.MovieInfo
import io.reactivex.Observable

interface MovieRepository {

    fun getPopularMovieList(page: Int?): Observable<List<MovieInfo>>

    fun getUpcomingMovieList(page: Int?): Observable<List<MovieInfo>>

    fun getAllFavouriteMovieList(page: Int?): Observable<List<MovieInfo>>
}