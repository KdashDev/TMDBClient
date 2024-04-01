package com.soulqubit.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.soulqubit.tmdbclient.data.model.tvshow.TvShow
import com.soulqubit.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.soulqubit.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TvShowViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
) : ViewModel() {
    fun getTvShows(): Observable<List<TvShow>> {
        return Observable.create { emitter ->
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tvShows = getTvShowsUseCase.execute()
                    if (tvShows != null) {
                        emitter.onNext(tvShows)
                    }
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }
    }


    fun updateTvShows() = liveData {
        val tvShowList = updateTvShowsUseCase.execute()
        emit(tvShowList)
    }
}
