package com.decagon.moviehut.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.moviehut.R
import javax.xml.transform.Templates

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.movies_title)
        val releaseDate = view.findViewById<TextView>(R.id.movie_date)
        val rating = view.findViewById<TextView>(R.id.rating)
        val image = view.findViewById<ImageView>(R.id.movie_poster)
        val favorites = view.findViewById<ImageView>(R.id.movie_favourite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

    }
}