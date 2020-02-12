package com.decagon.moviehut.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.Utils
import com.decagon.moviehut.controllers.adapters.MoviesAdapter
import com.decagon.moviehut.data.movieresponse.Movie
import com.decagon.moviehut.viewmodels.HomeViewModel


class HomeFragment : Fragment(), MoviesAdapter.OnItemClickedListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private val utils = Utils


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        val adapter = MoviesAdapter(
            activity!!.applicationContext,
            this
        )
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val favouriteSwitch: Switch = view.findViewById(R.id.favourites_switch)
        val layoutManager = GridLayoutManager(context, 2)



        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.allMovies.observe(this, Observer<List<Movie>> { movies ->

            adapter.movies = movies
            adapter.notifyDataSetChanged()
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        //
        favouriteSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                viewModel.allMovies.observe(this, Observer<List<Movie>> { movies ->

                    adapter.movies = movies
                    adapter.notifyDataSetChanged()
                })
                recyclerView.adapter = adapter
            } else {
                viewModel.favouriteMovies.observe(this, Observer<List<Movie>> { movies ->
                    adapter.movies = movies
                    adapter.notifyDataSetChanged()
                })
            }
        }


        //recyclerView.addOnScrollListener(recyclerViewOnScrollListener)

        return view
    }

    private fun loadMoreItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMovieClicked(position: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
            utils.convertToParcellable(viewModel.allMovies.value!![position])
        )
        findNavController().navigate(action)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

}
