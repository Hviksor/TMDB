package com.example.tmdb.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel

@Database(entities = [MovieModel::class], version = 1)
abstract class MovieRoomDataBas : RoomDatabase() {
    abstract fun getDao(): MovieDao


    companion object {
        private var movieDb: MovieRoomDataBas? = null
        fun getInstanceDb(context: Context): MovieRoomDataBas {
            return if (movieDb == null) {
                movieDb = Room.databaseBuilder(context, MovieRoomDataBas::class.java, "db").build()
                movieDb as MovieRoomDataBas

            } else {
                movieDb as MovieRoomDataBas
            }

        }
    }

}