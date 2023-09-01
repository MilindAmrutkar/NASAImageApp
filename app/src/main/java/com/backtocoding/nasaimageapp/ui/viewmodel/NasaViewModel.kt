package com.backtocoding.nasaimageapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backtocoding.nasaimageapp.core.network.ApiResponse
import com.backtocoding.nasaimageapp.domain.model.NasaImage
import com.backtocoding.nasaimageapp.domain.usecase.FetchNasaImageOfTheDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NasaViewModel @Inject constructor(
    private val fetchNasaImageOfTheDayUseCase: FetchNasaImageOfTheDayUseCase
) : ViewModel() {

    private val _nasaImage = MutableStateFlow<ApiResponse<NasaImage?>>(ApiResponse.Loading)
    val nasaImage: StateFlow<ApiResponse<NasaImage?>> = _nasaImage

    init {
        fetchImageOfTheDay()
    }

    private fun fetchImageOfTheDay(date: String? = null) {
        viewModelScope.launch {
            fetchNasaImageOfTheDayUseCase.execute(date).collect { result ->
                _nasaImage.value = result
            }
        }
    }

    fun refresh() {
        fetchImageOfTheDay()
    }
}