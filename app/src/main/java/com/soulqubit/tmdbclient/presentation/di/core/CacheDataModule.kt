package com.soulqubit.tmdbclient.presentation.di.core

import com.soulqubit.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.soulqubit.tmdbclient.data.repository.artist.datasourceImpl.ArtistCacheDataSourceImpl
import com.soulqubit.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.soulqubit.tmdbclient.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.soulqubit.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.soulqubit.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource {
        return TvShowCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistCacheDataSource(): ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }


}