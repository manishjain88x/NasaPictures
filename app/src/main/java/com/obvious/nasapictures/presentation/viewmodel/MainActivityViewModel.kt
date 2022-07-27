package com.obvious.nasapictures.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obvious.nasapictures.data.models.ImageData
import com.obvious.nasapictures.domain.GetImagesUseCase
import com.obvious.nasapictures.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCase: GetImagesUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<Resource<List<ImageData>>>(Resource.Loading())
    val uiState: StateFlow<Resource<List<ImageData>>> = _uiState

    enum class SortBy {
        ASCENDING,
        DESCENDING
    }


    fun getAllLatestImages() {
        viewModelScope.launch {
            Log.i("ViewModel thread", Thread.currentThread().name)
            useCase.execute().catch { e ->
                _uiState.value = Resource.Error(e.toString())
            }.collect {
                _uiState.value = Resource.Success(it)
            }
            /* imagesRepo.getImages().collect {
                 imagesLiveData.postValue(sortByDate(SortBy.DESCENDING, it))
             }*/
        }
    }

    private fun sortByDate(sortBy: SortBy, images: List<ImageData>): List<ImageData> {
        return when (sortBy) {
            SortBy.ASCENDING -> {
                images.sortedBy { getDate(it.date) }.toMutableList()
            }
            SortBy.DESCENDING -> {
                images.sortedByDescending { getDate(it.date) }.toMutableList()
            }
        }
    }

    private fun getDate(date: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.parse(date)?.time ?: 0
    }

}