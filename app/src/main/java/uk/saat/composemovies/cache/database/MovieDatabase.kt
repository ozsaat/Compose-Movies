package uk.saat.composemovies.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.saat.composemovies.cache.MovieDao
import uk.saat.composemovies.cache.model.MovieEntity
import uk.saat.composemovies.util.Converters

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        val DATABASE_BASE = "movie_db"
    }
}