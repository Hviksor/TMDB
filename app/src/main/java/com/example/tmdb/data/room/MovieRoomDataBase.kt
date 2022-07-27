package com.example.tmdb.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel

@Database(entities = [MovieModel::class], version = 6)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun getDao(): MovieDao

    companion object {
        private var movieDb: MovieRoomDataBase? = null

        @Synchronized
        fun getInstanceDb(context: Context): MovieRoomDataBase {
            return if (movieDb == null) {
                movieDb = Room.databaseBuilder(context, MovieRoomDataBase::class.java, "db").build()
                movieDb as MovieRoomDataBase

            } else {
                movieDb as MovieRoomDataBase
            }

        }
    }

}