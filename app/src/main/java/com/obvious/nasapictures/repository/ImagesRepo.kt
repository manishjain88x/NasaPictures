package com.obvious.nasapictures.repository

import com.obvious.nasapictures.models.ImageData
import kotlinx.coroutines.flow.Flow

interface ImagesRepo {
    suspend fun getImages(): Flow<List<ImageData>>
}