package com.decagon.moviehut.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.FavouriteMoviesAdapter
import com.decagon.moviehut.controllers.MoviesAdapter
import com.decagon.moviehut.data.database.FavouriteMovie
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
        val adapter2 = FavouriteMoviesAdapter(activity!!.applicationContext, this)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val favouriteSwitch: Switch = view.findViewById(R.id.favourites_switch)



        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        if (!favouriteSwitch.isActivated){
            viewModel.allMovies.observe(this, Observer<List<Movie>>{movies ->

                adapter.movies = movies
                adapter.notifyDataSetChanged()
            })
            recyclerView.adapter = adapter
        }else{
            viewModel.favouriteMovies.observe(this, Observer<List<FavouriteMovie>>{movies ->
                adapter2.movies = movies
                adapter.notifyDataSetChanged()
            })
            recyclerView.adapter = adapter2
        }




        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onMovieClicked(position: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(viewModel.allMovies.value!![position])
        findNavController().navigate(action)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
