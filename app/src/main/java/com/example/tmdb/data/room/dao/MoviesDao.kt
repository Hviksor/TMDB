package com.example.tmdb.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tmdb.models.MovieItem

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieItem: MovieItem)

    @Delete
    suspend fun delete(movieItem: MovieItem)

    @Query("SELECT * from movie_table")
    fun getAllMovies(): LiveData<List<MovieItem>>


}