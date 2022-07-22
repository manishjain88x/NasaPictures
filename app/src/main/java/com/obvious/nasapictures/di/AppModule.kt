package com.obvious.nasapictures.di

import android.app.Application
import com.obvious.nasapictures.repository.ImagesRepo
import com.obvious.nasapictures.repository.ImagesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun repository(context: Application): ImagesRepo = ImagesRepoImpl(context)

}