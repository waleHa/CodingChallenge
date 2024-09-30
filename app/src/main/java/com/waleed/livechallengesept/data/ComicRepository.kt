package com.waleed.livechallengesept.data

import retrofit2.Response
import javax.inject.Inject

class ComicRepository @Inject constructor(private val comicService: ComicService){
    suspend fun fetchComic(): Response<ComicModel> {
        return comicService.fetchComic()
    }
}