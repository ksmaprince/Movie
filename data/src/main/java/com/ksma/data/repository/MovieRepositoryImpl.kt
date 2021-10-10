package com.ksma.data.repository

import com.ksma.data.datasource.local.CacheMovieDataSource
import com.ksma.data.datasource.remote.NetworkMovieDataSource
import com.ksma.data.mapper.MovieMapper
import com.ksma.data.storage.InternalStorageManager
import com.ksma.domain.model.MovieInfo
import com.ksma.domain.repository.MovieRepository
import io.reactivex.Observable

class MovieRepositoryImpl(
    private val networkMovieDataSource: NetworkMovieDataSource,
    private val cacheMovieDataSource: CacheMovieDataSource,
    private val internalStorageManager: InternalStorageManager,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun getPopularMovieList(page: Int?): Observable<List<MovieInfo>> {
        return cacheMovieDataSource.getAllPopularMovies()
            .flatMap {
                if (it.isEmpty()) fetchPopularMovieFromRemote()
                else Observable.just(movieMapper.mapPopularMovieEntitiesToMovieInfos(it))
            }
    }

    override fun getUpcomingMovieList(page: Int?): Observable<List<MovieInfo>> {
        return cacheMovieDataSource.getAllUpComingMovies()
            .flatMap {
                if (it.isEmpty()) fetchUpComingMovieFromRemote()
                else Observable.just(movieMapper.mapUpcomingMovieEntitiesToMovieInfos(it))
            }
    }

    override fun getAllFavouriteMovieList(page: Int?): Observable<List<MovieInfo>> {
        return cacheMovieDataSource.getAllFavouriteMovies().map {
            movieMapper.mapFavouriteMovieEntitiesToMovieInfos(it)
        }
    }


    private fun fetchPopularMovieFromRemote(): Observable<List<MovieInfo>> {
        return networkMovieDataSource.getPopularMovies(null)
            .map {
                it.results
            }
            .flatMap {
                val movies = movieMapper.mapMovieResponseToMovieInfos(it)
                val popularEntities = movieMapper.mapMovieInfosToPopularMovieEntities(movies)
                cacheMovieDataSource.savePopularMovies(popularEntities)
            }
            .map {
                movieMapper.mapPopularMovieEntitiesToMovieInfos(it)
            }
    }


    private fun fetchUpComingMovieFromRemote(): Observable<List<MovieInfo>> {
        return networkMovieDataSource.getUpcomingMovies(null)
            .map {
                it.results
            }
            .flatMap {
                val movies = movieMapper.mapMovieResponseToMovieInfos(it)
                val upcomingEntities = movieMapper.mapMovieInfosToUpcomingMovieEntities(movies)
                cacheMovieDataSource.saveUpComingMovies(upcomingEntities)
            }
            .map {
                movieMapper.mapUpcomingMovieEntitiesToMovieInfos(it)
            }
    }
}