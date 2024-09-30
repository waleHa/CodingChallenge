package com.waleed.livechallengesept.ui.detailedcomic

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.waleed.livechallengesept.data.ComicModel
import com.waleed.livechallengesept.ui.ComicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedComicScreen(
    navController: NavController,
    viewModel: ComicViewModel = hiltViewModel()
) {
    val comic by viewModel.comicSuccess.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Comic Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (comic == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            ComicDetails(
                comicModel = comic!!,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ComicDetails(comicModel: ComicModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        // Display the comic image
        comicModel.img?.let {
            AsyncImage(
                model = it,
                contentDescription = comicModel.alt ?: "Comic Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(bottom = 16.dp)
            )
        }

        // List of label-value pairs
        val details = listOf(
            "Title" to comicModel.title,
            "Alt Text" to comicModel.alt,
            "Transcript" to comicModel.transcript,
            "Comic Number" to comicModel.num?.toString(),
            "Date" to "${comicModel.day}/${comicModel.month}/${comicModel.year}",
            "Link" to comicModel.link,
            "News" to comicModel.news,
            "Safe Title" to comicModel.safeTitle
        )

        // Iterate over the details and display them
        details.forEach { (label, value) ->
            value?.takeIf { it.isNotBlank() }?.let {
                LabeledText(label = label, value = it)
            }
        }
    }
}

@Composable
fun LabeledText(label: String, value: String, modifier: Modifier = Modifier) {
    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.padding(bottom = 8.dp)
    )
}
