package uk.saat.composemovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uk.saat.composemovies.cache.MovieDao
import uk.saat.composemovies.cache.model.MovieEntityMapper
import uk.saat.composemovies.interactors.movie_list.GetMovies
import uk.saat.composemovies.network.MovieService
import uk.saat.composemovies.network.model.MovieDtoMapper

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideGetMovies(
        movieService: MovieService,
        movieDao: MovieDao,
        movieEntityMapper: MovieEntityMapper,
        movieDtoMapper: MovieDtoMapper
    ): GetMovies {
        return GetMovies(
            movieDao = movieDao,
            movieService = movieService,
            entityMapper = movieEntityMapper,
            dtoMapper = movieDtoMapper
        )
    }
}