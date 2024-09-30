package com.waleed.livechallengesept.data

import retrofit2.Response
import retrofit2.http.GET


interface ComicService {
    @GET("info.0.json")
    suspend fun fetchComic(): Response<ComicModel>
}