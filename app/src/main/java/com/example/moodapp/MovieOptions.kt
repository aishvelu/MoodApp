package com.example.moodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MovieOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_options)
        val exampleMovieList = mutableListOf(
            Movie(name="Frozen", genre= "Children", cover="frozen"),
            Movie(name="Moana", genre= "Children", cover="moana"),
            Movie(name="Coco", genre= "Children", cover="coco"),
            Movie(name="Finding Nemo", genre= "Children", cover="nemo"),
            Movie(name="The Lion King", genre= "Children", cover="lionking"),
            Movie(name="Tangled", genre= "Children", cover="tangled")
        )
        val intentLauncher2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->
            //TO DO
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyMovieAdapter(exampleMovieList, this, intentLauncher2)
    }
}