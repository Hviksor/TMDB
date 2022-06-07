package com.example.tmdb.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.room.dao.MoviesDao
import com.example.tmdb.models.MovieItem

@Database(entities = [MovieItem::class], version = 3)
abstract class MoviesRoomDataBase : RoomDatabase() {
    abstract fun getDao(): MoviesDao


    companion object {
        private var database: MoviesRoomDataBase? = null
        fun getInstanceDb(context: Context): MoviesRoomDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    MoviesRoomDataBase::class.java,
                    "db"
                ).build()
                database as MoviesRoomDataBase
            } else {
                database as MoviesRoomDataBase
            }
        }

    }

}