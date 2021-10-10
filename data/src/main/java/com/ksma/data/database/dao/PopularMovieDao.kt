package com.ksma.data.database.dao

import androidx.room.*
import com.ksma.data.database.entity.PopularMovieEntity
import io.reactivex.Single


@Dao
interface PopularMovieDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(entityPopulars: List<PopularMovieEntity>): List<Long>

    @Update
    fun updatePopularMovies(popularMovieEntity: PopularMovieEntity)

    @Query("SELECT * FROM movie_popular")
    fun getAllPopularMovies(): Single<List<PopularMovieEntity>>
}
