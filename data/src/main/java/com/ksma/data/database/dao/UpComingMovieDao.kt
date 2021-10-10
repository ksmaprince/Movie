package com.ksma.data.database.dao

import androidx.room.*
import com.ksma.data.database.entity.UpComingMovieEntity
import io.reactivex.Single

@Dao
interface UpComingMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpComingMovies(entityUpcoming: List<UpComingMovieEntity>): List<Long>

    @Update
    fun updateUpComingMovie(upComingMovieEntity: UpComingMovieEntity)

    @Query("SELECT * FROM movie_upcoming")
    fun getAllUpComingMovies(): Single<List<UpComingMovieEntity>>
}
