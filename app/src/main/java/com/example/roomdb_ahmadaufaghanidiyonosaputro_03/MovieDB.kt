package com.example.roomdb_ahmadaufaghanidiyonosaputro_03

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdb_ahmadaufaghanidiyonosaputro_03.room.Movie
import com.example.roomdb_ahmadaufaghanidiyonosaputro_03.room.MovieDao
import java.util.concurrent.locks.Lock

@Database (
    entities = [Movie::class],
    version = 1
)

abstract class MovieDB() : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
       @Volatile private var instance : MovieDB? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDB::class.java,
            "movie12345.db"
        ).build()

        }
    }