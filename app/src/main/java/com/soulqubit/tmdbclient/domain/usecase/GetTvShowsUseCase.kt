package com.soulqubit.tmdbclient.domain.usecase

import com.soulqubit.tmdbclient.data.model.tvshow.TvShow
import com.soulqubit.tmdbclient.domain.repository.TvShowRepository
import io.reactivex.Observable
class GetTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute():List<TvShow>?=tvShowRepository.getTvShows()



}