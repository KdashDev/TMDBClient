package com.soulqubit.tmdbclient.presentation.di.core

import com.soulqubit.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.soulqubit.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.soulqubit.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.soulqubit.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource
import com.soulqubit.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.soulqubit.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.soulqubit.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.soulqubit.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.soulqubit.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.soulqubit.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.soulqubit.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.soulqubit.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDatasource
import com.soulqubit.tmdbclient.domain.repository.ArtistRepository
import com.soulqubit.tmdbclient.domain.repository.MovieRepository
import com.soulqubit.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDatasource: MovieRemoteDatasource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {

        return MovieRepositoryImpl(
            movieRemoteDatasource,
            movieLocalDataSource,
            movieCacheDataSource
        )


    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        tvShowRemoteDatasource: TvShowRemoteDatasource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {

        return TvShowRepositoryImpl(
            tvShowRemoteDatasource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )


    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        artistRemoteDatasource: ArtistRemoteDatasource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistRepository {

        return ArtistRepositoryImpl(
            artistRemoteDatasource,
            artistLocalDataSource,
            artistCacheDataSource
        )


    }

}