package com.obvious.nasapictures.data.repository

import com.obvious.nasapictures.data.models.ImageData
import kotlinx.coroutines.flow.Flow

interface ImagesRepo {
    suspend fun getImages(): Flow<List<ImageData>>
}