package com.ksma.data.database

import android.content.Context
import androidx.room.Room
import com.ksma.data.database.dao.FavouriteMovieDao
import com.ksma.data.database.dao.PopularMovieDao
import com.ksma.data.database.dao.UpComingMovieDao

class RoomDbHelper constructor(private val context: Context) {


    private val moviDaoPopular: PopularMovieDao

    private val movieDaoUpComing: UpComingMovieDao

    private val movieDaoFavourite: FavouriteMovieDao


    init {
        val rxDatabase: RxDatabase = Room.databaseBuilder(
            context, RxDatabase::class.java,
            "moviedb"
        )
            .fallbackToDestructiveMigration()
            .build()
        moviDaoPopular = rxDatabase.popularMovieDao()
        movieDaoUpComing = rxDatabase.upComingMovieDao()
        movieDaoFavourite = rxDatabase.favouriteMovieDao()
    }

    fun getPopularMovieDao(): PopularMovieDao = moviDaoPopular

    fun getUpComingMovieDao(): UpComingMovieDao = movieDaoUpComing

    fun getFavouriteMovieDao(): FavouriteMovieDao = movieDaoFavourite

}