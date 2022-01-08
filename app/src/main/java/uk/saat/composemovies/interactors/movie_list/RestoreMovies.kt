package uk.saat.composemovies.interactors.movie_list

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.saat.composemovies.cache.MovieDao
import uk.saat.composemovies.cache.model.MovieEntityMapper
import uk.saat.composemovies.domain.data.DataState
import uk.saat.composemovies.domain.model.Movie
import uk.saat.composemovies.util.MOVIE_PAGINATION_PAGE_SIZE
import uk.saat.composemovies.util.TAG

class RestoreMovies(
    private val movieDao: MovieDao,
    private val entityMapper: MovieEntityMapper
) {
    fun execute(
        page: Int
    ): Flow<DataState<List<Movie>>> = flow {
        try {
            emit(DataState.loading())
            val cacheResult =
                movieDao.restoreAllMovies(
                    pageSize = MOVIE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            val list = entityMapper.fromEntityList(cacheResult)
            emit(DataState.success(list))
        } catch (e: Exception) {
            Log.e(TAG, "execute: ${e.message}")
            emit(DataState.error<List<Movie>>(e.message ?: "Unknown error"))
        }
    }
}