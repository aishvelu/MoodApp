package com.example.moodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lec6demo.R

class MovieOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_options)
//        val exampleMovieList = mutableListOf(
//            Movie(name="Frozen", genre= "Children", cover="frozen"),
//            Movie(name="Moana", genre= "Children", cover="moana"),
//            Movie(name="Coco", genre= "Children", cover="coco"),
//            Movie(name="Finding Nemo", genre= "Children", cover="nemo"),
//            Movie(name="The Lion King", genre= "Children", cover="lionking"),
//            Movie(name="Tangled", genre= "Children", cover="tangled")
//        )
        var mood = ""
        val intentLauncher2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->
            val currMood = results.data?.extras?.getString("Mood")
            if (currMood != null) {
                mood = currMood
            }
        }
        val adapter = MyMovieAdapter(MovieRepository.movieList, this, intentLauncher2)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        getMovie(mood) { myMovie ->
            runOnUiThread {
                // Stuff that updates the UI
                val list = myMovie;
                for (n in list){
                    MovieRepository.addMovie(n, adapter)
                }
            }
        }
    }
}