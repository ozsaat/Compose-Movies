package uk.saat.composemovies.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.saat.composemovies.cache.MovieDao
import uk.saat.composemovies.cache.database.MovieDatabase
import uk.saat.composemovies.cache.model.MovieEntityMapper
import uk.saat.composemovies.presentation.BaseApplication
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): MovieDatabase {
        return Room
            .databaseBuilder(app, MovieDatabase::class.java, MovieDatabase.DATABASE_BASE)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(app: MovieDatabase): MovieDao {
        return app.movieDao()
    }

    @Singleton
    @Provides
    fun provideCacheMovieMapper(): MovieEntityMapper {
        return MovieEntityMapper()
    }
}