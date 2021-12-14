package uk.saat.composemovies.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.saat.composemovies.cache.model.MovieEntity
import uk.saat.composemovies.util.MOVIE_PAGINATION_PAGE_SIZE

@Dao
interface MovieDao {

    @Insert
    suspend fun insertSingleMovie(movie: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListOfMovies(movies: List<MovieEntity>): LongArray

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("DELETE FROM movies WHERE id IN (:ids)")
    suspend fun deleteMovies(ids: List<Int>): Int

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM movies WHERE id = :primaryKey")
    suspend fun deleteMovie(primaryKey: Int): Int

    /**
     * Retrieve movies for a particular page
     */
    @Query("""
        SELECT * FROM movies
        WHERE title LIKE '%' || :query || '%'
        LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
    """)
    suspend fun searchMovies(
        query: String,
        page: Int,
        pageSize: Int = MOVIE_PAGINATION_PAGE_SIZE
    ): List<MovieEntity>

    /**
     * Same as 'searchMovies' function, but no query.
     */
    @Query("""
        SELECT * FROM movies
        LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
    """)
    suspend fun getAllMovies(
        page: Int,
        pageSize: Int = MOVIE_PAGINATION_PAGE_SIZE
    ): List<MovieEntity>

    /**
     * Restore Movies after process death
     */
    @Query("""
        SELECT * FROM movies
        WHERE title LIKE '%' || :query || '%'
        LIMIT (:page * :pageSize)
    """)
    suspend fun restoreMovies(
        query: String,
        page: Int,
        pageSize: Int = MOVIE_PAGINATION_PAGE_SIZE
    ): List<MovieEntity>

    /**
     * Same as 'restoreMovies' function, but no query.
     */
    @Query("""
        SELECT * FROM movies
        LIMIT (:page * :pageSize)
    """)
    suspend fun restoreAllMovies(
        page: Int,
        pageSize: Int = MOVIE_PAGINATION_PAGE_SIZE
    ): List<MovieEntity>
}