package com.decagon.moviehut.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.Utils
import com.decagon.moviehut.controllers.repositories.URLRepository
import com.decagon.moviehut.data.movieresponse.Movie
import com.decagon.moviehut.viewmodels.DetailsViewModel
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val utils = Utils
        val movie: Movie = arguments!!.get("parcellable_movie") as Movie


        view.findViewById<TextView>(R.id.details_title).text = movie.title
        view.findViewById<TextView>(R.id.details_overview).text = movie.overview
        view.findViewById<TextView>(R.id.rating).text = movie.voteAverage.toString()
        view.findViewById<TextView>(R.id.details_release_date)
            .append("\n${utils.convertDate(movie.releaseDate)}")
        Glide.with(context!!)
            .load(URLRepository.IMAGE_BASE_URL + "original" + movie.backdropPath)
            .placeholder(R.drawable.no_image)
            .into(view.findViewById(R.id.movie_poster))


        val favourites = view.findViewById<ImageView>(R.id.detaials_favourite)
        val saveButton = view.findViewById<Button>(R.id.favourites_save_button)
        val removeButton = view.findViewById<Button>(R.id.favourites_remove_button)
        val watchTrailerButton = view.findViewById<ImageButton>(R.id.watch_trailer_button)


        if (movie.isFavourite) {
            favourites.setImageResource(R.drawable.ic_favorite_filled)
            saveButton.visibility = View.GONE
            removeButton.visibility = View.VISIBLE
        } else {
            favourites.setImageResource(R.drawable.ic_favorite_outline)
            saveButton.visibility = View.VISIBLE
            removeButton.visibility = View.GONE
        }



        saveButton.setOnClickListener {
            movie.isFavourite = true
            viewModel.saveFavourite(movie)
            saveButton.visibility = View.GONE
            removeButton.visibility = View.VISIBLE
            favourites.setImageResource(R.drawable.ic_favorite_filled)
        }

        removeButton.setOnClickListener {
            movie.isFavourite = false
            viewModel.saveFavourite(movie)
            favourites.setImageResource(R.drawable.ic_favorite_outline)
            saveButton.visibility = View.VISIBLE
            removeButton.visibility = View.GONE
        }

        watchTrailerButton.setOnClickListener {
            val intent = Intent(this.activity?.applicationContext, TrailerActivity::class.java)
            intent.putExtra("movie_id", movie.id)
            startActivity(intent)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        book_ticket_button.setOnClickListener {
            openPopup()
        }
    }

    private fun openPopup() {
        val dialog = MyDialog()
        dialog.show(activity!!.supportFragmentManager, "")
    }



}
