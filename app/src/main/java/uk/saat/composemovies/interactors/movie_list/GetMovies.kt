package uk.saat.composemovies.interactors.movie_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.saat.composemovies.cache.MovieDao
import uk.saat.composemovies.cache.model.MovieEntityMapper
import uk.saat.composemovies.domain.data.DataState
import uk.saat.composemovies.domain.model.Movie
import uk.saat.composemovies.network.MovieService
import uk.saat.composemovies.network.model.MovieDtoMapper
import uk.saat.composemovies.util.MOVIE_PAGINATION_PAGE_SIZE

// This class is to get a list of movies using the "discover" api call
class GetMovies(
    private val movieDao: MovieDao,
    private val movieService: MovieService,
    private val entityMapper: MovieEntityMapper,
    private val dtoMapper: MovieDtoMapper
) {

    fun execute(
        apiKey: String,
        page: Int,
        isNetworkAvailable: Boolean
    ): Flow<DataState<List<Movie>>> = flow {
        try {
            emit(DataState.loading())

//            if (isNetworkAvailable) {
                val movies = getMoviesFromNetwork(
                    page = page,
                    apiKey = apiKey
                )

                movieDao.insertListOfMovies(entityMapper.toEntityList(movies))
//            }

            val cacheResult = movieDao.getAllMovies(pageSize = MOVIE_PAGINATION_PAGE_SIZE, page = page)

            val list = entityMapper.fromEntityList(cacheResult)
            emit(DataState.success(list))
        } catch (e: Exception) {
            emit(DataState.error<List<Movie>>(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getMoviesFromNetwork(
        apiKey: String,
        page: Int,
    ): List<Movie> {
        return dtoMapper.toDomainList(
            movieService.get(
                page = page,
                apiKey = apiKey
            ).movies
        )
    }
}