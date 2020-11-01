package com.example.shoppinglisttesting.repositories


import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.ibtikartask.remote.Model.listpeaple.PeapleResponse
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.ibtikartask.remote.Model.person.PersonResponse
import com.example.ibtikartask.remote.pagingsource.MoviePagingSource
import com.example.shoppinglisttesting.other.Constants.MAX_SIZE
import com.example.shoppinglisttesting.other.Constants.PAGE_SIZE
import com.example.shoppinglisttesting.other.Resource
import com.example.shoppinglisttesting.remote.MovieAPI
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    private val movieAPI: MovieAPI,
) : MovieRepository {
    override fun popularPeaple(page :Int): LiveData<PagingData<Result>> {

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieAPI,page) }
        ).liveData

    }

    override suspend fun testPopularPeaple(): Resource<PeapleResponse> {
        return try {
            val response = movieAPI.popularPeaple(1)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)

            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt reach to the  server, Check Internet Connection", null)
        }

    }


    override suspend fun getPerson(id: Int): Resource<PersonResponse> {

        return try {
            val response = movieAPI.getPerson(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)

            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt reach to the  server, Check Internet Connection", null)
        }
    }


}