package com.obvious.nasapictures.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obvious.nasapictures.models.ImageData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.lang.reflect.Type

class ImagesRepoImpl(private val context: Application) : ImagesRepo {
    override suspend fun getImages(): Flow<List<ImageData>> {
        val jsonString = loadJSONFromAsset()
        val gson = Gson()
        val type: Type = object : TypeToken<List<ImageData>>() {}.type
        val images = gson.fromJson(jsonString, type) as List<ImageData>
        return MutableStateFlow(images)
    }

    private fun loadJSONFromAsset(): String {
        val fileName = "data.json"
        val jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return jsonString
    }
}