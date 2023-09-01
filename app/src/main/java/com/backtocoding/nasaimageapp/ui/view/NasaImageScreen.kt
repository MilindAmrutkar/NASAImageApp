package com.backtocoding.nasaimageapp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.backtocoding.nasaimageapp.R
import com.backtocoding.nasaimageapp.core.network.ApiResponse
import com.backtocoding.nasaimageapp.ui.viewmodel.NasaViewModel

@Composable
fun NasaImageScreen(date: String, viewModel: NasaViewModel = hiltViewModel()) {
    val nasaImage by viewModel.nasaImage.collectAsState()

    when (val result = nasaImage) {
        is ApiResponse.Loading -> {
            CircularProgressIndicator()
        }

        is ApiResponse.Error -> {
            result.exception.message?.let { Text(text = it) }
        }

        is ApiResponse.Success -> {
            val imageData = result.data
            if (imageData?.date == date) {
                NasaImageContent(
                    title = imageData.title ?: "",
                    date = imageData.date,
                    description = imageData.explanation ?: "",
                    imageUrl = imageData.url ?: ""
                )
            }

        }
    }
}

@Composable
fun NasaImageContent(
    title: String,
    date: String,
    description: String,
    imageUrl: String
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(text = title)
        }
        item { Text(text = date) }
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl).build(),
                contentDescription = "Nasa Image",
                placeholder = painterResource(id = R.drawable.ic_error),
                error = painterResource(id = R.drawable.ic_error)
            )
        }
        item { Text(text = description) }
    }

}