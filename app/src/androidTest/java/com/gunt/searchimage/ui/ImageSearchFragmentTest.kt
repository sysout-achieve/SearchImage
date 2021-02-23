package com.gunt.searchimage.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gunt.searchimage.R
import com.gunt.searchimage.launchFragmentInHiltContainer
import com.gunt.searchimage.ui.imagesearch.ImageSearchFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ImageSearchFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        launchFragmentInHiltContainer<ImageSearchFragment>()
    }

    @Test
    fun fragmentInViewTest() {
        onView(withId(R.id.edit_search)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_image)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_empty)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun editTextChangeTest() {
        // given
        onView(withId(R.id.edit_search)).perform(click())

        // when
        onView(withId(R.id.edit_search)).perform(typeTextIntoFocusedView("hi"))

        // then
        onView(withId(R.id.edit_search)).check(matches(ViewMatchers.withText("hi")))
    }
}
