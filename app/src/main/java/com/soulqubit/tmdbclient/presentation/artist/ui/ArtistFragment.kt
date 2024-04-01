package com.soulqubit.tmdbclient.presentation.artist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.soulqubit.tmdbclient.R
import com.soulqubit.tmdbclient.data.model.artist.Artist
import com.soulqubit.tmdbclient.databinding.FragmentArtistBinding
import com.soulqubit.tmdbclient.presentation.artist.adapter.ArtistAdapter
import com.soulqubit.tmdbclient.presentation.di.Injector
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArtistFragment : Fragment() {

    companion object {
        private val TAG = ArtistFragment::class.java.simpleName
    }

    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as Injector).createArtistSubComponent()
            .inject(this)
        artistViewModel = ViewModelProvider(this, factory)[ArtistViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentArtistBinding.inflate(inflater, container, false)
        val view = binding.root

        collectArtistFlows()
        initRecyclerView()

        return view
    }

    private fun collectArtistFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    artistViewModel.artistList.collect {
                        displayPopularArtists(it)
                    }
                }
                launch {
                    artistViewModel.updateArtistList.collect {
                        if (it != null) {
                            Log.i(TAG, "observed $it")
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
        binding.artistRecyclerView.layoutManager =
            LinearLayoutManager(requireContext().applicationContext)
        adapter = ArtistAdapter()
        binding.artistRecyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.IO) {
            artistViewModel.getArtists()
        }
    }

    private fun displayPopularArtists(artistList: List<Artist>?) {
        binding.artistProgressBar.visibility = View.VISIBLE
        if (artistList != null) {
            Log.i(TAG, "observed $artistList")
            adapter.setList(artistList)
            adapter.notifyDataSetChanged()
            binding.artistProgressBar.visibility = View.GONE
        } else {
            binding.artistProgressBar.visibility = View.GONE
            Toast.makeText(
                requireContext().applicationContext,
                R.string.no_data_available,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}