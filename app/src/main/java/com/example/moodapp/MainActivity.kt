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
        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->
            // Nothing
        }
        val whimButton: Button = findViewById(R.id.whimsical)
        whimButton.setOnClickListener{
            var intent = Intent(this, MovieOptions::class.java)
            intent.putExtra("Mood", "whimsical")
            intentLauncher.launch(intent)
        }
        val romButton: Button = findViewById(R.id.romantic)
        romButton.setOnClickListener{
            var intent = Intent(this, MovieOptions::class.java)
            intent.putExtra("Mood", "romantic")
            intentLauncher.launch(intent)
        }
        val advButton: Button = findViewById(R.id.adventurous)
        advButton.setOnClickListener{
            var intent = Intent(this, MovieOptions::class.java)
            intent.putExtra("Mood", "adventurous")
            intentLauncher.launch(intent)
        }
        val sadButton: Button = findViewById(R.id.sad)
        sadButton.setOnClickListener{
            var intent = Intent(this, MovieOptions::class.java)
            intent.putExtra("Mood", "sad")
            intentLauncher.launch(intent)
        }
    }
}