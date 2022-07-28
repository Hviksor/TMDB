package com.example.tmdb.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel

@Database(entities = [MovieModel::class], version = 7)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao


    companion object {

        private var movieDB: MovieRoomDataBase? = null

        @Synchronized
        fun getInstanceDB(context: Context): MovieRoomDataBase {
            return if (movieDB == null) {
                movieDB = Room.databaseBuilder(context, MovieRoomDataBase::class.java, "db").build()
                movieDB as MovieRoomDataBase
            } else {
                movieDB as MovieRoomDataBase
            }

        }
    }

}