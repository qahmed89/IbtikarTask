package com.example.ibtikartask.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingData
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.ibtikartask.R
import com.example.ibtikartask.adapter.PeapleAdapter
import com.example.ibtikartask.getOrAwaitValuex
import com.example.ibtikartask.launchFragmentInHiltContainer
import com.example.ibtikartask.remote.Model.listpeaple.PeapleResponse
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.ibtikartask.repoistory.FakeMovieRepository
import com.example.ibtikartask.ui.MovieViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class PeapleFragmentTest {

    private var job: Job? = null

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
    fun clickItem_NavigateTODeTailsFragment () {
        val navController = Mockito.mock(NavController::class.java)


        val movieApi= PagingData
        val result = listOf(Result(false,1,1, listOf(),"","Ahmed",0.0,"TEST"))
        val paging =  movieApi.from(result)
        var testViewModel= MovieViewModel(FakeMovieRepository())

        launchFragmentInHiltContainer<PeapleFragment>(fragmentFactory = fragmentFactory) {
            viewModels = testViewModel

            Navigation.setViewNavController(requireView(), navController)

            job?.cancel()
            lifecycleScope.launch {

                    peapleAdapter.submitData(viewLifecycleOwner.lifecycle,paging)


            }
            viewModels.testPopluarPeaple()
        }
        Espresso.onView(ViewMatchers.withId(R.id.rvPeapleFragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PeapleAdapter.PeapleViewHolder>(
                0,
                ViewActions.click()

            )


        )
        Mockito.verify(navController).navigate(PeapleFragmentDirections.actionPeapleFragmentToDetailsFragment(result.get(0)))
        val value =testViewModel.popularPeaple.getOrAwaitValuex().getContentIfNotHandled().let {result->
            result?.data?.results
        }

        assertThat(value).isEqualTo(result)


    }
}