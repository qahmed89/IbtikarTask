package com.example.ibtikartask.ui.fragment

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingData
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.ibtikartask.R
import com.example.ibtikartask.adapter.ImageAdapter
import com.example.ibtikartask.getOrAwaitValuex
import com.example.ibtikartask.launchFragmentInHiltContainer
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.ibtikartask.repoistory.FakeMovieRepository
import com.example.ibtikartask.ui.MovieViewModel
import com.google.common.truth.Truth.assertThat
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
class DetailsFragmentTest {

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
    fun clickOnImage_navigateToMovieImageFragment() {

        val navController = Mockito.mock(NavController::class.java)
        val imageUrl = "TEST"
        val testViewModel= MovieViewModel(FakeMovieRepository(), SavedStateHandle())
        val result = Result(false, 1, 1, listOf(), "", "Ahmed", 0.0, "TEST")
        val args = Bundle().apply {
            putParcelable("result", result)
        }
        launchFragmentInHiltContainer<DetailsFragment>(
            fragmentFactory = fragmentFactory,
            fragmentArgs = args
        ) {

            Navigation.setViewNavController(requireView(), navController)
            imageAdapter.image = listOf(imageUrl)
            viewModels = testViewModel
            viewModels.testPopluarPeaple()

        }
        Espresso.onView(withId(R.id.rvDetailsFragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(
                0,
                ViewActions.click()

            )

        )
        Mockito.verify(navController)
            .navigate(DetailsFragmentDirections.actionDetailsFragmentToMovieImageFragment("https://image.tmdb.org/t/p/w600_and_h900_bestv2TEST"))
        val value =
            testViewModel.popularPeaple.getOrAwaitValuex().getContentIfNotHandled().let { result ->
                result?.data?.results?.get(0)
            }
        assertThat(value?.profile_path).isEqualTo(imageUrl)
    }
}