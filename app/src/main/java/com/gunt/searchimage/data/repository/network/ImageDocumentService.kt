package com.gunt.searchimage.data.repository.network

import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.network.response.ResponseKakao
import com.gunt.searchimage.di.KAKAO_API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val REQUEST_IMAGE_LIST_SIZE_DEFAULT: Int = 30
const val REQUEST_IMAGE_LIST_TYPE_DEFAULT: String = "accuracy"

interface ImageDocumentService {
    @GET("/v2/search/image")
    @Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    fun fetchImage(
            @Query("query") title: String,
            @Query("sort") sort: String = REQUEST_IMAGE_LIST_TYPE_DEFAULT,
            @Query("page") page: Int,
            @Query("size") size: Int = REQUEST_IMAGE_LIST_SIZE_DEFAULT
    ): Single<ResponseKakao<ImageDocument>>
}
