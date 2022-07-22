package com.obvious.nasapictures.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obvious.nasapictures.models.ImageData
import com.obvious.nasapictures.repository.ImagesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val imagesRepo: ImagesRepo) : ViewModel() {
    val imagesLiveData = MutableLiveData<List<ImageData>>()

    enum class SortBy {
        ASCENDING,
        DESCENDING
    }


    fun getAllLatestImages() {
        viewModelScope.launch {
            imagesRepo.getImages().collect {
                imagesLiveData.postValue(sortByDate(SortBy.DESCENDING, it))
            }
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