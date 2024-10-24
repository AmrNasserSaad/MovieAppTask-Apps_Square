package com.example.movieapptask.presentation.details


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapptask.R
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.presentation.ui.theme.Axiforma
import com.example.movieapptask.presentation.ui.theme.ButtonColor
import com.example.movieapptask.utils.Const.HOME_SCREEN

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movie: Movie
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box{

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                contentDescription = movie.title,
                placeholder = painterResource(id = R.drawable.movie_img),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(28.dp)
                    .size(32.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(ButtonColor)
                    .padding(4.dp)
                    .clickable {
                        navController.navigate(HOME_SCREEN)
                    }

            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(modifier = Modifier.padding(horizontal = 28.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    fontFamily = Axiforma,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp
                )
                Text(
                    text = "355 Reviews",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black,
                    fontSize = 12.sp,
                    modifier = Modifier.alpha(0.8f)
                )

            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Marvel Studios",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.alpha(0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.alpha(0.4f),
            )
        }


    }

}


/*
//@Composable
//fun CastCard(name: String, role: String, imageUrl: Int) {
//    Card(
//        shape = RoundedCornerShape(10.dp),
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth(),
//        colors = CardDefaults.cardColors(Color.Transparent)
//
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .padding(8.dp)
//                .border(
//                    1.dp, BackgroundColor, CircleShape
//                )
//        ) {
//            // Profile Image
//            Image(
//                painter = painterResource(id = imageUrl),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(50.dp)
//                    .clip(CircleShape)
//                    .border(
//                        1.5.dp, Brush.linearGradient(
//                            listOf(
//                                Color(0xFF19A1BE),
//                                Color(0xFF7D4192)
//                            )
//                        ), CircleShape
//                    ),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//
//            // Cast Name and Role
//
//            Column {
//                Text(
//                    text = name,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "As $role",
//                    color = Color.Gray,
//                    modifier = Modifier.padding(end = 16.dp)
//                )
//            }
//
//
//        }
//    }
//}
//
//@Composable
//fun CastGrid(castList: List<CastMember>) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        modifier = Modifier.padding(8.dp),
//        contentPadding = PaddingValues(8.dp)
//    ) {
//        items(castList.size) { castMember ->
//            CastCard(
//                name = "scksm",
//                role = "scsmi",
//                imageUrl = R.drawable.movie_img
//            )
//        }
//    }
//}
//
//data class CastMember(
//    val name: String,
//    val role: String,
//    val imageUrl: String
//)*/

@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        rememberNavController(),
        movie = Movie(
            id = 1,
            title = "",
            overview = "",
            poster_path = "",
            backdrop_path = "",
            release_date = "",
            vote_average = 0.0,
            genre_ids = emptyList()
        )
    )
}
