package com.ksma.data.datasource.local

import com.ksma.data.database.RoomDbHelper
import com.ksma.data.database.entity.FavouriteMovieEntity
import com.ksma.data.database.entity.PopularMovieEntity
import com.ksma.data.database.entity.UpComingMovieEntity
import io.reactivex.Observable

class CacheMovieDataSource(private val dbHelper: RoomDbHelper) {

    fun savePopularMovies(entityPopulars: List<PopularMovieEntity>): Observable<List<PopularMovieEntity>> {
        dbHelper.getPopularMovieDao().insertPopularMovies(entityPopulars)
        return dbHelper.getPopularMovieDao().getAllPopularMovies().toObservable()
    }

    fun getAllPopularMovies(): Observable<List<PopularMovieEntity>> {
        return dbHelper.getPopularMovieDao().getAllPopularMovies().toObservable()
    }

    fun saveUpComingMovies(entityUpcomings: List<UpComingMovieEntity>): Observable<List<UpComingMovieEntity>> {
        dbHelper.getUpComingMovieDao().insertUpComingMovies(entityUpcomings)
        return dbHelper.getUpComingMovieDao().getAllUpComingMovies().toObservable()
    }

    fun getAllUpComingMovies(): Observable<List<UpComingMovieEntity>> {
        return dbHelper.getUpComingMovieDao().getAllUpComingMovies().toObservable()
    }

    fun getAllFavouriteMovies(): Observable<List<FavouriteMovieEntity>> {
        return dbHelper.getFavouriteMovieDao().getAllFavouriteMovies().toObservable()
    }
}