package com.example.netflix.di

import com.example.data.BuildConfig
import com.example.remote.apiservice.ApiService
import com.example.util.APIKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoviesInterceptor(): Interceptor {
        return Interceptor { it ->
            val call = it.request()
            val response = call.url.newBuilder()
                .addQueryParameter(
                    APIKeys.APIKey
                    , BuildConfig.API_KEY)
                .build()
            val request = call.newBuilder()
                .url(response).build()
            it.proceed(request)

        }
    }

    @Provides
    @Singleton
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level= HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor,
                            loggingInterceptor: HttpLoggingInterceptor)
            : OkHttpClient {
        val okHttpClient =  OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
        return okHttpClient
    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.MODIES_BASE_URL)
            .client(okHttpClient)
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        val apiService =retrofit.create(ApiService::class.java)
        return apiService
    }


}