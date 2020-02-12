package com.decagon.moviehut.controllers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.repositories.URLRepository
import com.decagon.moviehut.data.movieresponse.Movie

class MoviesAdapter(val context: Context, var onItemClickedListener: OnItemClickedListener): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    var movies: List<Movie> = listOf()

    class MoviesViewHolder(view: View, onItemClickedListener: OnItemClickedListener) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.movies_title)
        val releaseDate = view.findViewById<TextView>(R.id.movie_date)
        val rating = view.findViewById<TextView>(R.id.movies_rating)
        val image = view.findViewById<ImageView>(R.id.movies_image)
        val favorites = view.findViewById<ImageView>(R.id.movie_favourite)

        init{
            view.setOnClickListener {
                onItemClickedListener.onMovieClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
        return MoviesViewHolder(
            view,
            onItemClickedListener
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.title
        holder.rating.text = movie.voteAverage.toString()
        holder.releaseDate.text = movie.releaseDate
        Glide.with(context)
            .load(URLRepository.IMAGE_BASE_URL +"w154" + movie.posterPath)
            .into(holder.image)

        if (movie.isFavourite){
            holder.favorites.setImageResource(R.drawable.ic_favorite_filled)
        }
    }

    interface OnItemClickedListener{
        fun onMovieClicked(position: Int)
    }
}