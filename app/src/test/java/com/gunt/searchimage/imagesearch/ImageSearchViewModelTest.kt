package com.gunt.searchimage.imagesearch

import androidx.paging.PagedList
import com.google.common.truth.Truth.assertThat
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.network.REQUEST_IMAGE_LIST_SIZE_DEFAULT
import com.gunt.searchimage.data.repository.network.response.ResponseKakao
import com.gunt.searchimage.data.repository.network.response.ResponseMeta
import com.gunt.searchimage.repository.FakeImageRepository
import com.gunt.searchimage.ui.imagesearch.ImageSearchViewModel
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Field

class ImageSearchViewModelTest {

    private val total_count = 6
    private val is_end = false
    private val pageableCount = 2

    lateinit var imageSearchViewModel: ImageSearchViewModel
    lateinit var responseKakao: ResponseKakao<ImageDocument>

    @Before
    fun setUp() {
        val imageList = mutableListOf(
            ImageDocument(
                display_sitename = "네이버블로그",
                datetime = "2020-08-08T14:18:05.000+09:00",
                image_url = "https://search3.kakaocdn.net/argon/130x130_85_c/JC8LrBAhH4Q"
            ),
            ImageDocument(
                display_sitename = "네이버블로그",
                datetime = "2020-08-08T14:18:05.000+09:00",
                image_url = "https://search2.kakaocdn.net/argon/130x130_85_c/ETvWnjL98F2"
            ),
            ImageDocument(
                display_sitename = "네이버블로그",
                datetime = "2020-08-08T14:18:05.000+09:00",
                image_url = "https://search1.kakaocdn.net/argon/130x130_85_c/IXJezql9ZEV"
            ),
            ImageDocument(
                display_sitename = "Daum카페",
                datetime = "2020-08-08T14:18:05.000+09:00",
                image_url = "https://search2.kakaocdn.net/argon/130x130_85_c/IReAcFKrzyX"
            ),
            ImageDocument(
                display_sitename = "Daum블로그",
                datetime = "2020-08-09T14:18:05.000+09:00",
                image_url = "https://search1.kakaocdn.net/argon/130x130_85_c/5yJCVPPDnpF"
            ),
            ImageDocument(
                display_sitename = "Daum블로그",
                image_url = "https://search4.kakaocdn.net/argon/130x130_85_c/DJiIXGi0rWH"
            )
        )
        val responseMeta =
            ResponseMeta(total_count = total_count, is_end = is_end, pageableCount = pageableCount)

        responseKakao = ResponseKakao(responseMeta, imageList)

        imageSearchViewModel = ImageSearchViewModel(FakeImageRepository(imageList))
    }

    @Test
    fun countFetchingPageSize() {
        //  given
        //  expected = REQUEST_IMAGE_LIST_SIZE_DEFAULT = 30
        val expected = REQUEST_IMAGE_LIST_SIZE_DEFAULT
        val field: Field = imageSearchViewModel.javaClass.getDeclaredField("config")
        field.isAccessible = true
        val config: PagedList.Config = field.get(imageSearchViewModel) as PagedList.Config

        //  then
        assertThat(config.pageSize).isEqualTo(expected)
    }
}
