package com.example.tmdb.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.tmdb.domain.model.MovieModel

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieModel: MovieModel)

    @Delete
    suspend fun delete(movieModel: MovieModel)

    @Query("SELECT * from movie_table")
    fun getFavoriteMovieList(): LiveData<List<MovieModel>>
}