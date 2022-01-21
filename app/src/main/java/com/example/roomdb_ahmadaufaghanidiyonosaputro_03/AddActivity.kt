package com.example.roomdb_ahmadaufaghanidiyonosaputro_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.roomdb_ahmadaufaghanidiyonosaputro_03.room.Constant
import com.example.roomdb_ahmadaufaghanidiyonosaputro_03.room.Movie
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy { MovieDB(this) }
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupView()
        setupListener()

        Toast.makeText(this, movieId.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView() {
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                update_button.visibility = View.GONE
            }

            Constant.TYPE_UPDATE -> {
                save_button.visibility = View.GONE
                getMovie()
            }
        }
    }

    private fun setupListener() {
        save_button.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.movieDao().addMovie(
                    Movie(0, et_title.text.toString(), et_description.text.toString() )
                )
                finish()
            }
        }

        update_button.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.movieDao().updateMovie(
                    Movie(movieId, et_title.text.toString(), et_description.text.toString() )
                )
                finish()
            }
        }
    }

    fun getMovie() {
        movieId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getMovie(movieId)[0]
            et_title.setText(movies.title)
            et_description.setText(movies.description)
        }
    }
}