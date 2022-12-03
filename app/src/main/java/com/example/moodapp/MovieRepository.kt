package com.example.moodapp

object MovieRepository{
    var movieList: MutableList<Movie> = mutableListOf()
    fun addMovie(movie: Movie, adapter: MyMovieAdapter){
        movieList.add(movie)
        adapter.notifyItemInserted(movieList.size-1)
    }
    fun clearMovie(adapter: MyMovieAdapter){
        movieList.clear()
        adapter.notifyDataSetChanged()
    }
}