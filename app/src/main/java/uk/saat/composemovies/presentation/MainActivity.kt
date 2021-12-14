package uk.saat.composemovies.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.saat.composemovies.BuildConfig.API_KEY
import uk.saat.composemovies.network.MovieService

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieService::class.java)

        CoroutineScope(IO).launch {
            val resonse = service.get(
                1,
                API_KEY
            )
            Log.d("MainActivity", "onCreate: $resonse")
        }


//        setContent {
//            ComposeMoviesTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    DisplayMovies()
//                }
//            }
//        }
    }
}

@ExperimentalFoundationApi
@Composable
fun DisplayMovies() {

//    val movie = remember{MovieListData.movies}
//
//    LazyVerticalGrid(cells = GridCells.Fixed(2),
//        contentPadding = PaddingValues(
//            start = 12.dp,
//            top = 16.dp,
//            end = 12.dp,
//            bottom = 16.dp
//        ),
//        content = {
//            items(movie.size) { index ->
//                 MovieCard(movie = movie[index])
//            }
//        }
//    )
}