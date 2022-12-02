package com.example.moodapp

object MovieRepository{
    var movieList: MutableList<Movie> = mutableListOf(Movie(name = "Coco", genre = "children", cover = "https://media.newyorker.com/photos/5b23d8ad0a4f5b3d66492b14/16:9/w_1951,h_1097,c_limit/Tolentino-Coco.jpg" ))
    fun addMovie(movie: Movie, adapter: MyMovieAdapter){
        movieList.add(movie)
        adapter.notifyItemInserted(movieList.size-1)
    }
    fun clearMovie(){
        movieList.clear()
    }
}