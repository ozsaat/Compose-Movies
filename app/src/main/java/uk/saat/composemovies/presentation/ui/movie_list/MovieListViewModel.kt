package uk.saat.composemovies.presentation.ui.movie_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uk.saat.composemovies.domain.model.Movie
import uk.saat.composemovies.interactors.movie_list.GetMovies
import uk.saat.composemovies.interactors.movie_list.RestoreMovies
import uk.saat.composemovies.presentation.ui.movie_list.MovieListEvent.*
import uk.saat.composemovies.presentation.util.ConnectionManager
import uk.saat.composemovies.presentation.util.DialogQueue
import uk.saat.composemovies.presentation.util.TAG
import javax.inject.Inject
import javax.inject.Named

val PAGE_SIZE = 20

const val STATE_KEY_PAGE = "movie.state.page.key"
const val STATE_KEY_QUERY = "movie.state.query.key"
const val STATE_KEY_LIST_POSITION = "movie.state.query.list_position"

@HiltViewModel
class MovieListViewModel
@Inject
constructor(
    private val getMovies: GetMovies,
    private val restoreMovies: RestoreMovies,
    private val connectionManager: ConnectionManager,
    @Named("api_key") private val apiKey: String,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movies: MutableState<List<Movie>> = mutableStateOf(ArrayList())

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)

    var movieListScrollPosition = 0

    val dialogQueue = DialogQueue()

    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }

        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }

        onTriggerEvent(NewListEvent)
    }

    fun onTriggerEvent(event: MovieListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is NewListEvent -> {
                        newList()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    is RestoreStateEvent -> {
                        restoreState()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "onTriggerEvent: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private fun restoreState() {
        restoreMovies.execute(page = page.value).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                movies.value = list
            }

            dataState.error?.let { error ->
                Log.e(TAG, "restoreMovies: ${error}")
                dialogQueue.appendErrorMessage("Error", error)
            }
        }.launchIn(viewModelScope)
    }

    private fun newList() {
        getMovies.execute(
            apiKey = apiKey,
            page = page.value,
            connectionManager.isNetworkAvailable.value
        ).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                movies.value = list
            }

            dataState.error?.let { error ->
                Log.e(TAG, "getMovies: ${error}")
            }
        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        if ((movieListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            incrementPage()
            Log.d(TAG, "nextPage: triggered: ${page.value}")

            if (page.value > 1) {
                getMovies.execute(
                    apiKey = apiKey,
                    page = page.value,
                    connectionManager.isNetworkAvailable.value
                ).onEach { dataState ->
                    loading.value = dataState.loading
                    dataState.data?.let { list ->
                        appendMovies(list)
                    }
                    dataState.error?.let { error ->
                        Log.e(TAG, "nextPage: ${error}")
                        dialogQueue.appendErrorMessage("Error", error)
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setListScrollPosition(position: Int) {
        movieListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    private fun appendMovies(movies: List<Movie>) {
        val current = ArrayList(this.movies.value)
        current.addAll(movies)
        this.movies.value = current
    }
}