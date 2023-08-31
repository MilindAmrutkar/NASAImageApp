package com.backtocoding.nasaimageapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backtocoding.core.Result
import com.backtocoding.core.constants.Constants
import com.backtocoding.data.remote.NasaImageResponse
import com.backtocoding.data.repository.NasaImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NasaImageViewModel @Inject constructor(
    private val repository: NasaImageRepository
) : ViewModel() {

    private val _nasaImage =
        MutableStateFlow<Result<NasaImageResponse>>(Result.Error("Uninitialized"))
    val nasaImage: StateFlow<Result<NasaImageResponse>> get() = _nasaImage

    init {
        fetchImageOfTheDay()
    }

    private fun fetchImageOfTheDay() {
        viewModelScope.launch {
            val response = repository.fetchImageOfTheDay(Constants.API_KEY)
            _nasaImage.emit(response)
        }
    }
}