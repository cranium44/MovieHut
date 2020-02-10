package com.decagon.moviehut.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.MoviesAdapter
import com.decagon.moviehut.data.movieresponse.Movie
import com.decagon.moviehut.viewmodels.HomeViewModel

class HomeFragment : Fragment() , MoviesAdapter.OnItemClickedListener{

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.home_fragment, container, false)
        val adapter = MoviesAdapter(activity!!.applicationContext, this)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        viewModel.allMovies.observe(this, Observer<List<Movie>>{movies ->

            adapter.movies = movies
            adapter.notifyDataSetChanged()
        })



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onMovieClicked(position: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
    }

}
