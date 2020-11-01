package com.example.shoppinglisttesting.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.ibtikartask.remote.Model.listpeaple.PeapleResponse
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.ibtikartask.remote.Model.person.PersonResponse
import com.example.shoppinglisttesting.other.Resource

interface MovieRepository {


    fun popularPeaple(page : Int): LiveData<PagingData<Result>>
    suspend fun testPopularPeaple(): Resource<PeapleResponse>
    suspend fun getPerson(id: Int): Resource<PersonResponse>
}