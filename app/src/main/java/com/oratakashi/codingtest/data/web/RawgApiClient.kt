package com.oratakashi.codingtest.data.web

import com.oratakashi.codingtest.data.model.BaseResponse
import com.oratakashi.codingtest.data.model.DataItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RawgApiClient {
    @GET("games")
    fun getData(
        @Query("ordering") ordering: String,
        @Query("page") page: String = "1",
        @Query("page_size") page_size: String = "10",
        @Query("dates") dates: String = "2021-12-01,2021-12-31",
    ): Single<BaseResponse<DataItem>>
    @GET("games")

    fun searchData(
        @Query("ordering") ordering: String,
        @Query("search") search: String,
        @Query("page") page: String = "1",
        @Query("page_size") page_size: String = "10"
    ): Single<BaseResponse<DataItem>>
}