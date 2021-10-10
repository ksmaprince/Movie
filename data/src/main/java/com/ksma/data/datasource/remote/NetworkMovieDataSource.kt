package com.ksma.data.datasource.remote

import com.google.gson.Gson
import com.ksma.data.BuildConfig
import com.ksma.data.model.response.PopularMovieResponse
import com.ksma.data.model.response.UpComingMovieResponse
import com.ksma.data.network.RestAdapter
import com.ksma.data.network.service.MovieService
import com.ksma.domain.exception.NetworkException
import io.reactivex.Observable
import java.io.IOException

class NetworkMovieDataSource {

    private val movieService = RestAdapter.get().create(MovieService::class.java)

    fun getPopularMovies(page: Int?): Observable<PopularMovieResponse> {
        return movieService.getPopularMovies(BuildConfig.MOVIE_DB_KEY, page)
            .map {
                try {
                    if (it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(
                            it.errorBody()?.charStream(),
                            PopularMovieResponse::class.java
                        )
                        throw NetworkException.ApiException(errorResponse.status_message)
                    }
                } catch (e: IOException) {
                    throw NetworkException.DefaultException(e.localizedMessage)
                }
            }
    }

    fun getUpcomingMovies(page: Int?): Observable<UpComingMovieResponse> {
        return movieService.getUpComingMovies(BuildConfig.MOVIE_DB_KEY, page)
            .map {
                try {
                    if (it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(
                            it.errorBody()?.charStream(),
                            UpComingMovieResponse::class.java
                        )
                        throw NetworkException.ApiException(errorResponse.status_message)
                    }
                } catch (e: IOException) {
                    throw NetworkException.DefaultException(e.localizedMessage)
                }
            }
    }

}