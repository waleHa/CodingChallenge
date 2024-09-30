package com.waleed.livechallengesept.data


import com.google.gson.annotations.SerializedName

data class ComicModel(
    @SerializedName("alt")
    val alt: String? = "",
    @SerializedName("day")
    val day: String? = "",
    @SerializedName("img")
    val img: String? = "",
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("month")
    val month: String? = "",
    @SerializedName("news")
    val news: String? = "",
    @SerializedName("num")
    val num: Int? = 0,
    @SerializedName("safe_title")
    val safeTitle: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("transcript")
    val transcript: String? = "",
    @SerializedName("year")
    val year: String? = ""
)