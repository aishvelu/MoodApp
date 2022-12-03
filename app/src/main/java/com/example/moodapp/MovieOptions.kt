package com.example.moodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        var mood = "whimsical"
        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->
            val currMood = results.data?.extras?.getString("Mood")
            if (currMood != null) {
                mood = currMood
                Log.d("Mood", mood)
            }
        }
        val adapter = MyMovieAdapter(MovieRepository.movieList, this, intentLauncher)
        MovieRepository.clearMovie(adapter)
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
        MovieRepository.clearMovie(adapter)
    }
}