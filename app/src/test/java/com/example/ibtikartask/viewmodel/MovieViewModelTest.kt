package com.example.ibtikartask.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.ibtikartask.MainCoroutineRule
import com.example.ibtikartask.getOrAwaitValueTest
import com.example.ibtikartask.repositery.FakeRepository
import com.example.ibtikartask.ui.MovieViewModel
import com.example.shoppinglisttesting.other.Resource
import com.example.shoppinglisttesting.other.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.android.synthetic.main.fragment_peaple.*
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel(FakeRepository())
    }


    @Test
    fun `getData Person and Check , if return SUCCESS`(){
        viewModel.testPopluarPeaple()
        val value = viewModel.popularPeaple.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `getData Person and  , return SUCCESS ` (){
        viewModel.getPersonData(2888)
        val value = viewModel.person.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}