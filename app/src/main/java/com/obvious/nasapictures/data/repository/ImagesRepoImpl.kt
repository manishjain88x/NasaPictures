package com.obvious.nasapictures.data.repository

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obvious.nasapictures.data.models.ImageData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.reflect.Type

class ImagesRepoImpl(
    private val context: Application,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ImagesRepo {
    override suspend fun getImages(): Flow<List<ImageData>> = flow {
        val jsonString = loadJSONFromAsset()
        val gson = Gson()
        Log.i("ImagesRepoImpl thread", Thread.currentThread().name)
        val type: Type = object : TypeToken<List<ImageData>>() {}.type
        emit(gson.fromJson(jsonString, type) as List<ImageData>)
        // return@withContext gson.fromJson(jsonString, type) as List<ImageData>
    }.flowOn(defaultDispatcher)

    private fun loadJSONFromAsset(): String {
        val fileName = "data.json"
        val jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return jsonString
    }


    /* suspend fun getImages1(): List<ImageData> {
         withContext(defaultDispatcher) {
             val jsonString = loadJSONFromAsset()
             val gson = Gson()
             Log.i("ImagesRepoImpl thread", Thread.currentThread().name)
             val type: Type = object : TypeToken<List<ImageData>>() {}.type
             //  emit(gson.fromJson(jsonString, type) as List<ImageData>)
             return@withContext gson.fromJson(jsonString, type) as List<ImageData>
         }
     }*/
}