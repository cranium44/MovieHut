package com.decagon.moviehut.controllers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagon.moviehut.R
import com.decagon.moviehut.data.database.FavouriteMovie

class FavouriteMoviesAdapter(var context: Context, var onItemClickedListener: MoviesAdapter.OnItemClickedListener): RecyclerView.Adapter<FavouriteMoviesAdapter.FavouriteViewHolder>() {

    var movies: List<FavouriteMovie> = listOf()

    class FavouriteViewHolder(view: View, onItemClickedListener: MoviesAdapter.OnItemClickedListener) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.movies_title)
        val releaseDate = view.findViewById<TextView>(R.id.movie_date)
        val rating = view.findViewById<TextView>(R.id.movies_rating)
        val image = view.findViewById<ImageView>(R.id.movies_image)
        val favorites = view.findViewById<ImageView>(R.id.movie_favourite)
        val savebutton = view.findViewById<Button>()
        init{
            view.setOnClickListener {
                onItemClickedListener.onMovieClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
        return FavouriteViewHolder(view, onItemClickedListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.title
        holder.rating.text = movie.vote_average.toString()
        holder.releaseDate.text = movie.releaseDate
        Glide.with(context)
            .load(URLRepository.IMAGE_BASE_URL+"w154" + movie.posterPath)
            .into(holder.image)
    }
}