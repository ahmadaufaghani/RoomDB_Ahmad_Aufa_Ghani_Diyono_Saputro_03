package com.example.roomdb_ahmadaufaghanidiyonosaputro_03.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val description : String
)
