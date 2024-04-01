package com.soulqubit.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.soulqubit.tmdbclient.R
import com.soulqubit.tmdbclient.databinding.ActivityArtistBinding


class ArtistActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        //setupActionBarWithNavController(navController)

    }
    /**
     * Enables back button support. Simply navigates one element up on the stack.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

}