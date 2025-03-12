package com.example.netflix.di

import com.example.netflix.repository.remote.RemoteDataSource
import com.example.netflix.repository.remote.RemoteDataSourceImpl
import com.example.netflix.repository.room.LocalDataSource
import com.example.netflix.repository.room.LocalDataSourceImpl
import com.example.remote.apiservice.ApiService
import com.example.room.MovieDao
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
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(movieDao: MovieDao): LocalDataSource {
        return LocalDataSourceImpl(movieDao)
    }
}