package com.gunt.searchimage.repository

import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.data.repository.ImageRepository
import org.junit.Before
import org.junit.Test

class ImageRepositoryTest {

    lateinit var imageRepository: ImageRepository
    lateinit var imageList: MutableList<ImageDocument>

    @Before
    fun setUp() {
        imageList = mutableListOf(
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
        imageRepository = FakeImageRepository(imageList)
    }

    @Test
    fun searchImageListTest() {
        // when
        val responseKakao = imageRepository.fetchImage("", 1)

        // then
        responseKakao.map { it.documents }.test().assertResult(imageList)
    }
}
