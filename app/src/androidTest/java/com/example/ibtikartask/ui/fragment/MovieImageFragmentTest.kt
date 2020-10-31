package com.example.ibtikartask.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.GrantPermissionRule
import com.example.ibtikartask.R
import com.example.ibtikartask.launchFragmentInHiltContainer

import com.example.ibtikartask.repoistory.FakeMovieRepository
import com.example.ibtikartask.ui.MovieViewModel
import com.google.common.reflect.Reflection.getPackageName
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MovieImageFragmentTest {
    @get:Rule
    var grantPermissionRule: GrantPermissionRule =GrantPermissionRule.grant(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.INTERNET)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var fragmentFactory: MovieFragmentFactoryTest


    @Before
    fun setup() {
        hiltRule.inject()

    }
@Test
    fun ClickonButtonToSaveImage (){

        val navController = Mockito.mock(NavController::class.java)
        var testViewModel= MovieViewModel(FakeMovieRepository())
        val args = Bundle().apply {
            putString("url", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/qgjP2OrrX9gc6M270xdPnEmE9tC.jpg")
        }
        launchFragmentInHiltContainer<MovieImageFragment>(fragmentFactory = fragmentFactory ,fragmentArgs = args) {
            viewModels = testViewModel

            Navigation.setViewNavController(requireView(), navController)


        }

        Espresso.onView(ViewMatchers.withId(R.id.fbDownloadImage)).perform(

                    click()
        )


}
}