package com.gunt.searchimage

import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.ImageRepository
import com.gunt.searchimage.data.repository.network.response.ResponseKakao
import com.gunt.searchimage.data.repository.network.response.ResponseMeta
import io.reactivex.rxjava3.core.Single

class FakeImageRepository constructor(private var imageList: MutableList<ImageDocument>) :
    ImageRepository {

    val responseMeta = ResponseMeta(6, 2, false)

    override fun fetchImage(title: String, page: Int): Single<ResponseKakao<ImageDocument>> {
        return Single.just(ResponseKakao(responseMeta, imageList))
    }
}
