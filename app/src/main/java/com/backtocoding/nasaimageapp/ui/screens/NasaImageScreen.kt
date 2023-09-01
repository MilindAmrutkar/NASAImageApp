package com.backtocoding.nasaimageapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.backtocoding.core.Result
import com.backtocoding.nasaimageapp.NasaImageViewModel

@Composable
fun NasaImageScreen(viewModel: NasaImageViewModel = hiltViewModel()) {
    val nasaImageResult by viewModel.nasaImage.collectAsState()

    when (nasaImageResult) {
        is Result.Error -> {
        }

        is Result.Success -> {
            val nasaImage = (nasaImageResult as Result.Success).data

            Column {
                Image(
                    painter = rememberAsyncImagePainter(model = nasaImage.ur),
                    contentDescription = "NASA Image of the Day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = nasaImage.title,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = nasaImage.date,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = nasaImage.description,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}