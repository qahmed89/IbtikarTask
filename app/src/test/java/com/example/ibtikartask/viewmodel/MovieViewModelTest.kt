package com.example.ibtikartask.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.example.ibtikartask.MainCoroutineRule
import com.example.ibtikartask.getOrAwaitValueTest
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.ibtikartask.repositery.FakeRepository
import com.example.ibtikartask.ui.MovieViewModel
import com.example.shoppinglisttesting.other.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MovieViewModelTest  (  ) {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: MovieViewModel
    val x = SavedStateHandle()
    @Before
    fun setup() {
        viewModel = MovieViewModel(FakeRepository(),state = x)
    }


    @Test
    fun `getData popularPeaple and Check , if return SUCCESS`() {
        viewModel.testPopluarPeaple()
        val value = viewModel.popularPeaple.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }



    @Test
    fun `getData Person and  , return SUCCESS `() {
        viewModel.getPersonData(2888)
        val value = viewModel.person.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}