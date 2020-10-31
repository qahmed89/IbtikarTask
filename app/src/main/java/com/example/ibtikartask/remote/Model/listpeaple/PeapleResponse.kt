package com.example.ibtikartask.remote.Model.listpeaple

data class PeapleResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)