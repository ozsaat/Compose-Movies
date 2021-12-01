package uk.saat.composemovies.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uk.saat.composemovies.domain.model.Movie

@Composable
fun MovieCard(movie: Movie) {
//    Image(
//        painter = painterResource(id = movie.posterPath),
//        contentDescription = null,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .padding(4.dp)
//            .height(140.dp)
//            .width(100.dp)
//            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
//        )
}