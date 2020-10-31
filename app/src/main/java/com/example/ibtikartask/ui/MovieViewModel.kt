package com.example.ibtikartask.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ibtikartask.remote.Model.listpeaple.PeapleResponse
import com.example.ibtikartask.remote.Model.listpeaple.Result
import com.example.ibtikartask.remote.Model.person.PersonResponse
import com.example.shoppinglisttesting.other.Event
import com.example.shoppinglisttesting.other.Resource
import com.example.shoppinglisttesting.repositories.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val current =MutableLiveData<PagingData<Result>>()
     var currentSearchResult:LiveData<PagingData<Result>> =current
    private val _person = MutableLiveData<Event<Resource<PersonResponse>>>()
    val person: LiveData<Event<Resource<PersonResponse>>> = _person
    private val _popularPeaple = MutableLiveData<Event<Resource<PeapleResponse>>>()
    val popularPeaple: LiveData<Event<Resource<PeapleResponse>>> = _popularPeaple
    fun pagingPeaple(): LiveData<PagingData<Result>> {
        val newResult: LiveData<PagingData<Result>> = repository.popularPeaple()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return currentSearchResult

    }


    fun getPersonData(id: Int) {

        _person.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.getPerson(id)
            _person.value = Event(response)
        }
    }


    fun testPopluarPeaple(){

        _popularPeaple.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.testPopularPeaple()
            _popularPeaple.value = Event(response)
        }
    }


}

