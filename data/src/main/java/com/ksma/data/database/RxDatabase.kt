package com.ksma.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ksma.data.database.dao.FavouriteMovieDao
import com.ksma.data.database.dao.PopularMovieDao
import com.ksma.data.database.dao.UpComingMovieDao
import com.ksma.data.database.entity.FavouriteMovieEntity
import com.ksma.data.database.entity.PopularMovieEntity
import com.ksma.data.database.entity.UpComingMovieEntity

@Database(entities = [ PopularMovieEntity::class, UpComingMovieEntity::class, FavouriteMovieEntity::class], version = 1)
abstract class RxDatabase: RoomDatabase(){

    abstract fun popularMovieDao(): PopularMovieDao

    abstract fun upComingMovieDao(): UpComingMovieDao

    abstract fun favouriteMovieDao(): FavouriteMovieDao

}