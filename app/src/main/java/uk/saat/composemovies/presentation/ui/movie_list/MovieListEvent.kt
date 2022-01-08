package uk.saat.composemovies.presentation.ui.movie_list

sealed class MovieListEvent {

    object NewListEvent: MovieListEvent()

    object NextPageEvent: MovieListEvent()

    // restore after process death
    object RestoreStateEvent: MovieListEvent()
}