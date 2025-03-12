package com.example.netflix.di

import com.example.domain.repository.Repository
import com.example.domain.usecase.GetMovieDetails
import com.example.domain.usecase.GetNowPlayingMovies
import com.example.domain.usecase.GetPopularMovie
import com.example.domain.usecase.GetUpcomingMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideNowPlayingMovie(repository: Repository):GetNowPlayingMovies = GetNowPlayingMovies(repository)
    @Provides
    @Singleton
    fun provideUpcomingMovie(repository: Repository):GetUpcomingMovies = GetUpcomingMovies(repository)
    @Provides
    @Singleton
    fun providePopularMovie(repository: Repository):GetPopularMovie = GetPopularMovie(repository)
    @Provides
    @Singleton
    fun provideMovieDetails(repository: Repository):GetMovieDetails = GetMovieDetails(repository)
}