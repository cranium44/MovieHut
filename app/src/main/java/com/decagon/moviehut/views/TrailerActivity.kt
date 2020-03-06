package com.decagon.moviehut.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.repositories.URLRepository
import com.decagon.moviehut.viewmodels.TrailerViewModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_trailer.*

class TrailerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var playerView: YouTubePlayerView
    private lateinit var viewModel: TrailerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)

        playerView = findViewById<YouTubePlayerView>(R.id.trailer_player)
        viewModel = TrailerViewModel(this.application)

        play_trailer_button.setOnClickListener {
            Log.d("YOU TUBE", "player initialized")
            playerView.initialize(URLRepository.YOUTUBE_API_KEY, this)
        }

    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        Log.d("YOU TUBE", "Initialization successful")
        p1?.loadVideo(viewModel.getKey())
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.d("YOU TUBE", "Initialization Failed")
    }

}
