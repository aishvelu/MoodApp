package com.example.moodapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyMovieAdapter(
    val movieList : List<Movie>,
    val context : Context,
    val intentLauncher : ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<MyMovieAdapter.MyMovieViewHolder>() {

    class MyMovieViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val movieName : TextView = view.findViewById(R.id.movie_name)
        val movieGenre : TextView = view.findViewById(R.id.movie_genre)
        val movieImage : ImageView = view.findViewById(R.id.cover)
        val cardView : CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_cell, parent, false)
        return MyMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {
        val movie : Movie = movieList[position]
        val id = context.resources.getIdentifier(movie.cover, "drawable",
            context.packageName)
        holder.movieImage.setImageResource(id)
        holder.movieName.text = movie.name
        holder.movieGenre.text = movie.genre
        holder.cardView.setOnClickListener {
            val intent = Intent(context, MovieDescription::class.java)
            intent.putExtra("Cover", movie.cover)
            intent.putExtra("Name", holder.movieName.text.toString())
            intent.putExtra("Genre", holder.movieGenre.text.toString())
            intent.putExtra("Pos", position.toString())
            intentLauncher.launch(intent)

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}