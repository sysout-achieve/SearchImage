package com.gunt.searchimage.data.repository.network

import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.ImageRepository
import com.gunt.searchimage.data.repository.network.response.ResponseKakao
import io.reactivex.rxjava3.core.Single

class ImageRepositoryRemote
constructor(
        private var imageDocumentService: ImageDocumentService
) : ImageRepository {

    override fun fetchImage(title: String, page: Int): Single<ResponseKakao<ImageDocument>> {
        return imageDocumentService.fetchImage(title = title, page = page)
    }
}
