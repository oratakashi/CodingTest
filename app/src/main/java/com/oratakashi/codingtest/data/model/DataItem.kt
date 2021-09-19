package com.oratakashi.codingtest.data.model

import com.google.gson.annotations.SerializedName
import com.oratakashi.codingtest.domain.model.Games

data class DataItem(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("released") val released: String?,
    @SerializedName("background_image") val background_image: String?,
    @SerializedName("rating") val rating: String?
){
    fun toGames(): Games {
        return Games(id, name, released ?: "", background_image ?: "", rating ?: "")
    }
}
