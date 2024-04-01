package com.soulqubit.tmdbclient.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.soulqubit.tmdbclient.R
import com.soulqubit.tmdbclient.databinding.ActivityHomeBinding
import com.soulqubit.tmdbclient.presentation.artist.ArtistActivity
import com.soulqubit.tmdbclient.presentation.movie.MovieActivity
import com.soulqubit.tmdbclient.presentation.tv.TvShowActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepVisibleCondition{
                viewModel.isLoading.value
            }
        }


        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)

        supportActionBar?.hide() // Hide Action BAR

        binding.movieButton.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }

        binding.tvButton.setOnClickListener {
            val intent = Intent(this, TvShowActivity::class.java)
            startActivity(intent)
        }

        binding.artistsButton.setOnClickListener {
            val intent = Intent(this, ArtistActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        // Do nothing (prevents default back button behavior)
    }
}