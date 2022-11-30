package com.example.moodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->
            // TO DO
        }
        val happyButton: Button = findViewById(R.id.happy)
        happyButton.setOnClickListener{
            var intent = Intent(this, MovieOptions::class.java)
            intentLauncher.launch(intent)

        }
    }
}