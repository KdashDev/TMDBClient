package com.soulqubit.tmdbclient.presentation.di.movie

import com.soulqubit.tmdbclient.domain.usecase.GetMoviesUseCase
import com.soulqubit.tmdbclient.domain.usecase.UpdateMoviesUsecase
import com.soulqubit.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUsecase: UpdateMoviesUsecase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            getMoviesUseCase,
            updateMoviesUsecase
        )
    }

}