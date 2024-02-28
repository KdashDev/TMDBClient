package com.soulqubit.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager
import com.soulqubit.tmdbclient.R
import com.soulqubit.tmdbclient.data.model.artist.Artist
import com.soulqubit.tmdbclient.databinding.ActivityArtistBinding
import com.soulqubit.tmdbclient.presentation.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    private lateinit var binding: ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        (application as Injector).createArtistSubComponent()
            .inject(this)

        artistViewModel = ViewModelProvider(this, factory)[ArtistViewModel::class.java]

        lifeCycleHelper()

        collectArtistFlows()

        initRecyclerView()

    }

    private fun lifeCycleHelper() {
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                Log.d("ARTTAG", "onCreate")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                Log.d("ARTTAG", "onStart")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                Log.d("ARTTAG", "onResume")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                Log.d("ARTTAG", "onPause")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                Log.d("ARTTAG", "onStop")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                Log.d("ARTTAG", "onDestroy")
            }
        })
    }

    private fun collectArtistFlows() {
        lifecycleScope.launch {
            // Repetir esta corrutina mientras el estado del ciclo de vida sea STARTED
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    artistViewModel.artistList.collect {
                        Log.i("ARTTAG", "flow")
                        displayPopularArtists(it)
                    }
                }
                launch {
                    artistViewModel.updateArtistList.collect {
                        Log.i("ARTTAG", "flow2")
                        if (it != null) {
                            Log.i("ARTTAG", "observed $it")
                            adapter.setList(it)
                            adapter.notifyDataSetChanged()
                            binding.artistProgressBar.visibility = View.GONE
                        } else {
                            binding.artistProgressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }


    private fun initRecyclerView() {
        Log.i("ARTTAG", "artist activity init recycler view")
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.artistRecyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.IO) {
            artistViewModel.getArtists()
        }

    }


    private fun displayPopularArtists(artistList: List<Artist>?) {
        Log.i("ARTTAG", "artist activity display popular atrtist")
        binding.artistProgressBar.visibility = View.VISIBLE
        if (artistList != null) {
            Log.i("ARTTAG", "observed $artistList")
            adapter.setList(artistList)
            adapter.notifyDataSetChanged()
            binding.artistProgressBar.visibility = View.GONE
        } else {
            binding.artistProgressBar.visibility = View.GONE
            Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateTvShows()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun updateTvShows() {
        binding.artistProgressBar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            artistViewModel.updateArtists()

        }
    }
}