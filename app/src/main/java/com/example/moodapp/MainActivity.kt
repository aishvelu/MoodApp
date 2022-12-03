package com.example.moodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.moodapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mood = ""
        var intent = Intent(this, MovieOptions::class.java)
        val whimButton: Button = findViewById(R.id.whimsical)
        whimButton.setOnClickListener{
            mood = "whimsical"
            intent.putExtra("Mood", mood)
            startActivity(intent)
        }
        val romButton: Button = findViewById(R.id.romantic)
        romButton.setOnClickListener{
            mood = "romantic"
            intent.putExtra("Mood", mood)
            startActivity(intent)
        }
        val advButton: Button = findViewById(R.id.adventurous)
        advButton.setOnClickListener{
            mood ="adventurous"
            intent.putExtra("Mood", mood)
            startActivity(intent)
        }
        val sadButton: Button = findViewById(R.id.sad)
        sadButton.setOnClickListener{
            mood ="sad"
            intent.putExtra("Mood", mood)
            startActivity(intent)
        }
    }
}