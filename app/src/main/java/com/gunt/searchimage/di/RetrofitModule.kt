package com.gunt.searchimage.di

import com.gunt.searchimage.BuildConfig
import com.gunt.searchimage.data.repository.network.ImageDocumentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://dapi.kakao.com"

const val KAKAO_API_KEY = BuildConfig.KAKAO_KEY

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitApiService(): ImageDocumentService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ImageDocumentService::class.java)
    }
}
