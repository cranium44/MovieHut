package com.decagon.moviehut.viewmodels

import androidx.lifecycle.ViewModel
import com.decagon.moviehut.controllers.MoviesAdapter

class HomeViewModel : ViewModel() {

    private var data = ArrayList<String>()
    private var adapter = MoviesAdapter()

    fun getAdpter(): MoviesAdapter{
        return adapter
    }




}
