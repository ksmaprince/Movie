package com.ksma.data.database.dao

import androidx.room.*
import com.ksma.data.database.entity.FavouriteMovieEntity
import com.ksma.data.database.entity.PopularMovieEntity
import io.reactivex.Single

@Dao
interface FavouriteMovieDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteMovies(entities: List<FavouriteMovieEntity>): List<Long>

    @Delete
    fun deleteFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity)

    @Query("SELECT * FROM favourite")
    fun getAllFavouriteMovies(): Single<List<FavouriteMovieEntity>>
}
