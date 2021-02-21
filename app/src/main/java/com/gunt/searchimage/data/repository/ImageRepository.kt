package com.gunt.searchimage.data.repository

import com.gunt.searchimage.data.repository.network.response.ResponseKakao
import com.gunt.searchimage.data.domain.ImageDocument
import io.reactivex.rxjava3.core.Single

interface ImageRepository {
    fun fetchImage(title: String, page: Int): Single<ResponseKakao<ImageDocument>>
}
