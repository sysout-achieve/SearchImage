package com.gunt.searchimage.di

import com.gunt.searchimage.data.repository.ImageRepository
import com.gunt.searchimage.data.repository.network.ImageDocumentService
import com.gunt.searchimage.data.repository.network.ImageRepositoryRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideImageRepository(
        imageDocumentService: ImageDocumentService
    ): ImageRepository {
        return ImageRepositoryRemote(imageDocumentService = imageDocumentService)
    }
}
