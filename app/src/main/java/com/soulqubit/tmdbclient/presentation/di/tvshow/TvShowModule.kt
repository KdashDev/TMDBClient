package com.soulqubit.tmdbclient.presentation.di.tvshow

import com.soulqubit.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.soulqubit.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.soulqubit.tmdbclient.presentation.tv.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            getTvShowsUseCase,
            updateTvShowsUseCase
        )
    }

}