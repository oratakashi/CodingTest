package com.oratakashi.codingtest.data.web

import com.oratakashi.codingtest.data.model.BaseResponse
import com.oratakashi.codingtest.data.model.DataItem
import io.reactivex.Single

class RawgApi(private val endpoint: RawgApiClient) : RawgApiClient {
    override fun getData(
        ordering: String,
        page: String,
        page_size: String,
        dates: String
    ): Single<BaseResponse<DataItem>> {
        return endpoint.getData(ordering, page)
    }

    override fun searchData(
        ordering: String,
        search: String,
        page: String,
        page_size: String
    ): Single<BaseResponse<DataItem>> {
        return endpoint.searchData(ordering, search, page)
    }
}