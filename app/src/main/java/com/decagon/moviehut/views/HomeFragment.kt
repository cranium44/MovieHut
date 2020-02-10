package com.decagon.moviehut.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.MoviesAdapter
import com.decagon.moviehut.controllers.NetworkRepository
import com.decagon.moviehut.controllers.URLRepository
import com.decagon.moviehut.data.MovieDatabaseGenreAPI
import com.decagon.moviehut.data.movieresponse.Movie
import com.decagon.moviehut.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.home_fragment, container, false)
        val adapter = MoviesAdapter(activity!!.applicationContext)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        Log.d("Fragment", viewModel.allMovies.toString())
        viewModel.allMovies.observe(this, Observer<List<Movie>>{movies ->

            Log.d("Fragment2", viewModel.allMovies.toString())
            adapter.movies = movies
            adapter.notifyDataSetChanged()
        })



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

}
