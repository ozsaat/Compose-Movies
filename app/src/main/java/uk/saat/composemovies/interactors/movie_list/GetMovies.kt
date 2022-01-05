package uk.saat.composemovies.interactors.movie_list

import uk.saat.composemovies.cache.MovieDao
import uk.saat.composemovies.cache.model.MovieEntityMapper
import uk.saat.composemovies.network.MovieService
import uk.saat.composemovies.network.model.MovieDtoMapper

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
    ) {

    }
}