package com.example.ibtikartask.remote.pagingsource

import androidx.paging.PagingSource
import com.example.ibtikartask.remote.Model.listpeaple.PeapleResponse
import com.example.shoppinglisttesting.remote.MovieAPI
import retrofit2.HttpException
import java.io.IOException

private const val PEAPLE_STARTING_PAGE_INDEX = 1
lateinit var repo: PeapleResponse
class MoviePagingSource(
    private val api: MovieAPI, var page : Int
) : PagingSource<Int, com.example.ibtikartask.remote.Model.listpeaple.Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.ibtikartask.remote.Model.listpeaple.Result> {
        val position = params.key ?: page

        return try {
            val response = api.popularPeaple(position)
             response.body()?.let {
                repo = it
            }
            LoadResult.Page(
                data = repo.results,
                prevKey = if (position == PEAPLE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repo.results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}