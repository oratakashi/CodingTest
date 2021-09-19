package com.oratakashi.codingtest.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("results")
    val results: List<T>
)
