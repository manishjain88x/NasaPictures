package com.obvious.nasapictures.domain

import com.obvious.nasapictures.data.models.ImageData
import com.obvious.nasapictures.data.repository.ImagesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val repo: ImagesRepo) {
    suspend fun execute(): Flow<List<ImageData>> = repo.getImages()
}

/*
*
Data
Here we put the logic of bringing data either from local source or server.

Domain
Here we put the logic of business: convert, filter, mix and sort raw data that comes from Data layer to be ready and easy to handle in Presentation layer.

Presentation
Here we put the UI components and the logic that related to user interactions or navigation in order to get data from the user.*/