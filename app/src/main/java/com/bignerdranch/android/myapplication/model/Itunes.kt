package com.bignerdranch.android.myapplication.model


import com.google.gson.annotations.SerializedName

data class Itunes(
    @SerializedName("resultCount")
    val resultCount: Int?=null,
    @SerializedName("results")
    val results: List<Result>?=null
)