package com.decagon.moviehut.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.repositories.URLRepository
import com.decagon.moviehut.data.movieresponse.Movie
import com.decagon.moviehut.viewmodels.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)

        val movie: Movie = arguments!!.get("parcellable_movie") as Movie
        view.findViewById<TextView>(R.id.details_title).text = movie.title
        view.findViewById<TextView>(R.id.details_overview).text = movie.overview
        view.findViewById<TextView>(R.id.rating).text = movie.voteAverage.toString()
        view.findViewById<TextView>(R.id.details_release_date).append(" ${movie.releaseDate}")
        Glide.with(context!!)
            .load(URLRepository.IMAGE_BASE_URL + "original" + movie.backdropPath)
            .placeholder(R.drawable.no_image)
            .into(view.findViewById(R.id.movie_poster))

        Log.d("DET", movie.isFavourite.toString())

        if(movie.isFavourite){
            view.findViewById<ImageView>(R.id.detaials_favourite).setImageResource(R.drawable.ic_favorite_filled)
        }

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val savebutton = view.findViewById<Button>(R.id.favourites_save_button)
        savebutton.setOnClickListener {


            movie.isFavourite = true

            viewModel.saveFavourite(movie)
            this.findNavController().navigateUp()
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
