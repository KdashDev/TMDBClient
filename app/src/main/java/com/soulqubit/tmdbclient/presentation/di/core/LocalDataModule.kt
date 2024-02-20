package com.soulqubit.tmdbclient.presentation.di.core

import com.soulqubit.tmdbclient.data.db.ArtistDao
import com.soulqubit.tmdbclient.data.db.MovieDao
import com.soulqubit.tmdbclient.data.db.TvShowDao
import com.soulqubit.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.soulqubit.tmdbclient.data.repository.artist.datasourceImpl.ArtistLocalDataSourceImpl
import com.soulqubit.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.soulqubit.tmdbclient.data.repository.movie.datasourceImpl.MovieLocalDataSourceImpl
import com.soulqubit.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.soulqubit.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao):MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao):TvShowLocalDataSource{
        return TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao : ArtistDao):ArtistLocalDataSource{
        return ArtistLocalDataSourceImpl(artistDao)
    }


}