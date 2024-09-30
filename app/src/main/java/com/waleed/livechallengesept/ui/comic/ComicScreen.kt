package com.waleed.livechallengesept.ui.comic

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.waleed.livechallengesept.ui.ComicViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.waleed.livechallengesept.data.ComicModel

@Composable
fun ComicScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ComicViewModel = hiltViewModel(),
) {
    val comic by viewModel.comicSuccess.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            loading -> {
                CircularProgressIndicator()
            }

            error.isNotEmpty() -> {
                Text(text = error)
            }

            else -> {
                comic?.let { ShowUI(it, modifier, navController) }
            }
        }
    }
}

@Composable
fun ShowUI(comic: ComicModel, modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate("DetailedComicScreen")
            }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        comic.img?.let {
            AsyncImage(
                model = it,
                contentDescription = "Comic Image",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(350.dp)
            )
        }
        comic.month?.let {
            Text(
                text = "Month: $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFDDD222), shape = RoundedCornerShape(8.dp)),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        comic.year?.let {
            Text(
                text = "Year: $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFDDD222), shape = RoundedCornerShape(8.dp)),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
