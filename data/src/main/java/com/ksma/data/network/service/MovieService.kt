package com.ksma.data.network.service

import com.ksma.data.model.response.PopularMovieResponse
import com.ksma.data.model.response.UpComingMovieResponse
import com.ksma.data.network.URL
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(URL.POPULAR_MOVIES)
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int?
    ): Observable<Response<PopularMovieResponse>>

    @GET(URL.UPCOMING_MOVIES)
    fun getUpComingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int?
    ): Observable<Response<UpComingMovieResponse>>

}