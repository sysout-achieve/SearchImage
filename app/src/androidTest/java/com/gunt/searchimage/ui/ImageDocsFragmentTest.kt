package com.gunt.searchimage.ui

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gunt.searchimage.R
import com.gunt.searchimage.data.domain.ImageDocument
import com.gunt.searchimage.launchFragmentInHiltContainer
import com.gunt.searchimage.ui.imagedocs.ImageDocsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ImageDocsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val imageDocumentLongHeight = ImageDocument(
        display_sitename = "다음cafe",
        datetime = "2020-08-08T14:18:05.000+09:00",
        image_url = "https://t1.daumcdn.net/cfile/tistory/2420DE3B52E04A4A1A"
    )
    private val imageDocument = ImageDocument(
        display_sitename = "네이버블로그",
        datetime = "2020-08-08T14:18:05.000+09:00",
        image_url = "https://search3.kakaocdn.net/argon/130x130_85_c/JC8LrBAhH4Q"
    )

    @Before
    fun setUp() {
        hiltRule.inject()
        val bundle = Bundle()
        bundle.putSerializable("imageDoc", imageDocumentLongHeight)
        launchFragmentInHiltContainer<ImageDocsFragment>(bundle)
    }

    @Test
    fun fragmentInViewTest() {
        //       response 데이터에 출처 'display_sitename', 문서 작성 시간 'datetime'이 있을 경우 전체화면 이미지 밑에 표시해 줍니다.
        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.txtview_sitename)).check(matches(isDisplayed()))
        onView(withId(R.id.txtview_datetime)).check(matches(isDisplayed()))
        onView(withId(R.id.view_img_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun scrollImageTest() {
        onView(withId(R.id.scroll_view)).perform(swipeUp())
    }
}