package com.soulqubit.tmdbclient.presentation.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.soulqubit.tmdbclient.R
import com.soulqubit.tmdbclient.databinding.ActivityTvShowBinding
import com.soulqubit.tmdbclient.presentation.artist.ui.ArtistFragment
import com.soulqubit.tmdbclient.presentation.di.Injector
import javax.inject.Inject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class TvShowActivity : AppCompatActivity() {

    companion object {
        private val TAG = TvShowActivity::class.java.simpleName
    }

    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvAdapter
    private lateinit var binding: ActivityTvShowBinding
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
        (application as Injector).createTvShowSubComponent()
            .inject(this)

        tvShowViewModel= ViewModelProvider(this,factory)
            .get(TvShowViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.tvRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TvAdapter()
        binding.tvRecyclerView.adapter = adapter
        displayPopularTvShows()
    }

    private fun displayPopularTvShows(){
        binding.tvProgressBar.visibility = View.VISIBLE

        tvShowViewModel.getTvShows().observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(it!=null){
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.tvProgressBar.visibility = View.GONE
                }else{
                    binding.tvProgressBar.visibility = View.GONE
                    Toast.makeText(applicationContext,R.string.no_data_available, Toast.LENGTH_LONG).show()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.update,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_update -> {
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun updateTvShows(){
        binding.tvProgressBar.visibility = View.VISIBLE
        val response = tvShowViewModel.updateTvShows()
        response.observe(this, Observer {
            if(it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvProgressBar.visibility = View.GONE
            }else{
                binding.tvProgressBar.visibility = View.GONE
            }
        })
    }
}