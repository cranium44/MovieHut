package com.decagon.moviehut.views

//import com.google.android.youtube.player.YouTubePlayerView
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.decagon.moviehut.R
import com.decagon.moviehut.controllers.repositories.NetworkRepository
import com.decagon.moviehut.viewmodels.TrailerViewModel
import com.google.android.youtube.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrailerActivity : AppCompatActivity() {

    private lateinit var playerView: YouTubePlayerView
    private lateinit var viewModel: TrailerViewModel
    private lateinit var player: YouTubePlayer
    private lateinit var networkRepository: NetworkRepository
//    var videoID = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)

        viewModel = ViewModelProvider(this).get(TrailerViewModel::class.java)
        val id = intent.getIntExtra("movie_id", 0)
        supportActionBar?.apply {
            title = "Trailer"
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        networkRepository = NetworkRepository(application)

        playerView = findViewById<YouTubePlayerView>(R.id.trailer_player)
        lifecycle.addObserver(playerView);

        CoroutineScope(Dispatchers.Main).launch {
            initializePlayer(id)
        }


    }

//    override fun onDestroy() {
//        super.onDestroy()
//        playerView.release()
//    }

    private suspend fun initializePlayer(id: Int){
        withContext(Dispatchers.IO){
            val videoID = networkRepository.getTrailerUrls(id)
            Log.d("PLAYER", videoID)
            playerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                    Log.d("PLAYER", videoID)
                    youTubePlayer.cueVideo(videoID, 0f)
                }
            })
        }
    }

}
