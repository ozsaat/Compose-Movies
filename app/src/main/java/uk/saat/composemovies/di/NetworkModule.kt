package uk.saat.composemovies.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.saat.composemovies.BuildConfig
import uk.saat.composemovies.network.MovieService
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("api_key")
    fun provideApiKey(): String{
        return BuildConfig.API_KEY
    }

    @Singleton
    @Provides
    fun provideMovieService(): MovieService {

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieService::class.java)
    }
}