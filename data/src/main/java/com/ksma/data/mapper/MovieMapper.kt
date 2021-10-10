package com.ksma.data.mapper

import com.ksma.data.database.entity.FavouriteMovieEntity
import com.ksma.data.database.entity.PopularMovieEntity
import com.ksma.data.database.entity.UpComingMovieEntity
import com.ksma.data.model.response.MovieInfoResponse
import com.ksma.data.utils.DateUtils
import com.ksma.domain.model.MovieInfo

class MovieMapper(private val dateUtils: DateUtils) {

    fun mapMovieResponseToMovieInfos(list: List<MovieInfoResponse>): List<MovieInfo> {
        val movies = arrayListOf<MovieInfo>()
        list.forEach {
            val movieInfo = MovieInfo(
                it.vote_count,
                it.id,
                it.video,
                it.vote_average,
                it.title,
                it.popularity,
                "https://image.tmdb.org/t/p/w500${it.poster_path}",
                it.original_language,
                it.original_title,
                it.genre_ids,
                "https://image.tmdb.org/t/p/w500${it.backdrop_path}",
                it.adult,
                it.overview,
                dateUtils.convertDate(it.release_date)
            )
            movies.add(movieInfo)
        }
        return movies
    }

    fun mapMovieInfosToPopularMovieEntities(movies: List<MovieInfo>): List<PopularMovieEntity> {
        val entities = arrayListOf<PopularMovieEntity>()
        movies.forEach {
            val entity = PopularMovieEntity(
                it.id,
                it.title,
                it.overview,
                it.releaseDate,
                "https://image.tmdb.org/t/p/w500${it.posterPath}",
                "https://image.tmdb.org/t/p/w500${it.backdropPath}"
            )
            entities.add(entity)
        }
        return entities
    }

    fun mapPopularMovieEntitiesToMovieInfos(entities: List<PopularMovieEntity>): List<MovieInfo> {
        val movies = arrayListOf<MovieInfo>()
        entities.forEach {
            val movieInfo = MovieInfo(
                0,
                it.movieId,
                false,
                0.0,
                it.title,
                0.0,
                it.poster,
                "",
                "",
                arrayListOf(),
                it.originalPoster,
                false,
                it.overview,
                it.releaseDate
            )
            movies.add(movieInfo)
        }
        return movies
    }

    fun mapUpcomingMovieEntitiesToMovieInfos(entities: List<UpComingMovieEntity>): List<MovieInfo> {
        val movies = arrayListOf<MovieInfo>()
        entities.forEach {
            val movieInfo = MovieInfo(
                0,
                it.movieId,
                false,
                0.0,
                it.title,
                0.0,
                it.poster,
                "",
                "",
                arrayListOf(),
                it.originalPoster,
                false,
                it.overview,
                it.releaseDate
            )
            movies.add(movieInfo)
        }
        return movies
    }

    fun mapMovieInfosToUpcomingMovieEntities(movies: List<MovieInfo>): List<UpComingMovieEntity> {
        val entities = arrayListOf<UpComingMovieEntity>()
        movies.forEach {
            val entity = UpComingMovieEntity(
                it.id,
                it.title,
                it.overview,
                it.releaseDate,
                "https://image.tmdb.org/t/p/w500${it.posterPath}",
                "https://image.tmdb.org/t/p/w500${it.backdropPath}"
            )
            entities.add(entity)
        }
        return entities
    }

    fun mapFavouriteMovieEntitiesToMovieInfos(entities: List<FavouriteMovieEntity>): List<MovieInfo> {
        val movies = arrayListOf<MovieInfo>()
        entities.forEach {
            val movieInfo = MovieInfo(
                0,
                it.movieId,
                false,
                0.0,
                it.title,
                0.0,
                it.poster,
                "",
                "",
                arrayListOf(),
                it.originalPoster,
                false,
                it.overview,
                it.releaseDate
            )
            movies.add(movieInfo)
        }
        return movies
    }

    fun mapMovieInfosToFavouriteMovieEntities(movies: List<MovieInfo>): List<FavouriteMovieEntity> {
        val entities = arrayListOf<FavouriteMovieEntity>()
        movies.forEach {
            val entity = FavouriteMovieEntity(
                it.id,
                it.title,
                it.overview,
                it.releaseDate,
                "https://image.tmdb.org/t/p/w500${it.posterPath}",
                "https://image.tmdb.org/t/p/w500${it.backdropPath}"
            )
            entities.add(entity)
        }
        return entities
    }

    fun mapMovieInfoToFavouriteMovieEntity(movie: MovieInfo): FavouriteMovieEntity {
        val entity = FavouriteMovieEntity(
            movie.id,
            movie.title,
            movie.overview,
            movie.releaseDate,
            "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            "https://image.tmdb.org/t/p/w500${movie.backdropPath}"
        )
        return entity
    }

}