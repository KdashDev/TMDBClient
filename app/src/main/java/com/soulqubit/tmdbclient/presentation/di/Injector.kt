package com.soulqubit.tmdbclient.presentation.di

import com.soulqubit.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.soulqubit.tmdbclient.presentation.di.movie.MovieSubComponent
import com.soulqubit.tmdbclient.presentation.di.tvshow.TvShowSubComponent

interface Injector {
   fun createMovieSubComponent():MovieSubComponent
   fun createTvShowSubComponent():TvShowSubComponent
   fun createArtistSubComponent():ArtistSubComponent
}