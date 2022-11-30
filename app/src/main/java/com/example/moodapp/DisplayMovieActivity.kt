package com.example.moodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.lec6demo.R

class DisplayMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_movie)
        val name = findViewById<TextView>(R.id.displayname)
        val genre = findViewById<TextView>(R.id.displaygenre)
        val cover = findViewById<ImageView>(R.id.displaycover)

    }
}