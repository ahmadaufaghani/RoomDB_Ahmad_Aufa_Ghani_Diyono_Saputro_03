package com.example.roomdb_ahmadaufaghanidiyonosaputro_03.room

import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    suspend fun addMovie (mov:Movie)

    @Update
    suspend fun updateMovie (mov:Movie)

    @Delete
    suspend fun deleteMovie (mov:Movie)

    @Query ("SELECT * FROM movie")
    suspend fun getMovies():List<Movie>

    @Query ("SELECT * FROM movie WHERE id=:movie_id")
    suspend fun getMovie(movie_id: Int):List<Movie>
}