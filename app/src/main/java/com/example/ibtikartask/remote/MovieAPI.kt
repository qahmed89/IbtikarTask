package com.example.shoppinglisttesting.remote

import androidx.lifecycle.LiveData
import com.example.ibtikartask.BuildConfig
import com.example.ibtikartask.remote.Model.listpeaple.PeapleResponse
import com.example.ibtikartask.remote.Model.person.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("person/popular")
    suspend fun popularPeaple(
        @Query ("page") page : Int=1,
        @Query("language") language: String="en-US",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY

    ):Response< PeapleResponse>

    @GET("person/{person_id}")
    suspend fun getPerson(
        @Path("person_id") person_id : Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String="en-US"


    ):Response<PersonResponse>
}