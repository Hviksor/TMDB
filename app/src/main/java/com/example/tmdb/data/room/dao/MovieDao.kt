package com.example.tmdb.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.tmdb.domain.model.MovieModel

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieModel: MovieModel)

    @Delete
    fun delete(movieModel: MovieModel)

    @Query("SELECT * from movie_table")
    @JvmSuppressWildcards
    fun getFavoriteMovieList(): LiveData<List<MovieModel>>


    @Query("SELECT EXISTS(SELECT 1 from movie_table WHERE id=:movieId)")
    fun checkIsFavoriteMovie(movieId: Int): LiveData<Boolean>

}