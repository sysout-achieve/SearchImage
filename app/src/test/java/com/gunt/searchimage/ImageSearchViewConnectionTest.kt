package com.gunt.searchimage

import com.google.common.truth.Truth.assertThat
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.network.ImageRepositoryRemote
import com.gunt.searchimage.data.repository.network.REQUEST_IMAGE_LIST_SIZE_DEFAULT
import com.gunt.searchimage.di.RetrofitModule
import com.gunt.searchimage.ui.imagesearch.ImageSearchViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class ImageSearchViewConnectionTest {

    lateinit var imageSearchViewModel: ImageSearchViewModel

    @Before
    fun setUp() {
        imageSearchViewModel = ImageSearchViewModel(
            ImageRepositoryRemote(
                RetrofitModule.provideRetrofitApiService(RetrofitModule.provideOkHttpClient())
            )
        )
    }

    @Test
    fun searchImgListWithApiTest() {
        // given
        imageSearchViewModel.query = "혜리"
        val expected = REQUEST_IMAGE_LIST_SIZE_DEFAULT
        var list = listOf<ImageDocument>()

        // when
        val response = imageSearchViewModel.getImageFromRepository(imageSearchViewModel.query, imageSearchViewModel.page)
        response.subscribeOn(Schedulers.io())
            .subscribe(
                {
                    list = it.documents
                },
                {}
            )

        // then
        Thread.sleep(1000)
        response.test().awaitDone(5, TimeUnit.SECONDS).onComplete()

        assertThat(list.size).isEqualTo(expected)
    }
}
