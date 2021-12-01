package uk.saat.composemovies.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.saat.composemovies.domain.model.Movie

@ExperimentalFoundationApi
@Composable
fun MovieList(movies: Movie) {

//    Card(
//        modifier = Modifier
//            .padding(10.dp)
//            .fillMaxWidth(),
//        elevation = 10.dp,
//        shape = RoundedCornerShape(corner = CornerSize(10.dp))
//    ) {
//        MovieCard(movie = movies)
//    }
}