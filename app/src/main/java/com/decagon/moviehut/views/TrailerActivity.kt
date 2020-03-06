package com.decagon.moviehut.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.decagon.moviehut.R
import com.google.android.youtube.player.YouTubeBaseActivity

class TrailerActivity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
    }
}
