package com.gunt.searchimage.ui

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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

    @Before
    fun setUp() {
        hiltRule.inject()
        val bundle = Bundle()
        bundle.putSerializable("imageDoc", imageDocumentLongHeight)
        launchFragmentInHiltContainer<ImageDocsFragment>(bundle)
    }

    @Test
    fun fragmentInViewTest() {
        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.txtview_sitename)).check(matches(isDisplayed()))
        onView(withId(R.id.txtview_datetime)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTextViewTest() {
        onView(withId(R.id.txtview_sitename)).check(matches(withText(imageDocumentLongHeight.getSiteName())))
        onView(withId(R.id.txtview_datetime)).check(matches(withText(imageDocumentLongHeight.getDateTime())))
    }

    @Test
    fun scrollImageTest() {
        onView(withId(R.id.scroll_view)).perform(swipeUp())
    }
}
