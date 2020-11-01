package com.example.ibtikartask.repoistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ibtikartask.remote.Model.listpeaple.PeapleResponse
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.ibtikartask.remote.Model.person.PersonResponse
import com.example.shoppinglisttesting.other.Resource
import com.example.shoppinglisttesting.repositories.MovieRepository

class FakeMovieRepository : MovieRepository {


    val movieApi = PagingData
    val result = Result(false, 1, 1, listOf(), "", "Ahmed", 0.0, "TEST")
    val data = movieApi.from(listOf(result))
    val _paging = MutableLiveData<PagingData<Result>>(data)
    val paging: LiveData<PagingData<Result>> = _paging
    private var shouldReturnNetworkError = false



    override fun popularPeaple(page :Int): LiveData<PagingData<Result>> {

        return paging
    }

    override suspend fun testPopularPeaple(): Resource<PeapleResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(
                PeapleResponse(
                    1,
                    listOf(result),
                    100,
                    1000,

                )
            )
        }
    }



    override suspend fun getPerson(id: Int): Resource<PersonResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(
                PersonResponse(
                    false,
                    listOf(),
                    "",
                    "",
                    "",
                    2,
                    "",
                    1,
                    "",
                    "",
                    "",
                    "",
                    0.0,
                    ""
                )
            )
        }
    }
}