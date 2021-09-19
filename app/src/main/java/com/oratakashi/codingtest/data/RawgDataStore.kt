package com.oratakashi.codingtest.data

import com.oratakashi.codingtest.data.model.DataItem
import com.oratakashi.codingtest.data.web.RawgApi
import com.oratakashi.codingtest.utils.Order
import io.reactivex.Single

class RawgDataStore(
    private val webService: RawgApi
) : RawgRepository{
    override fun getLatest(page: Int): Single<List<DataItem>> {
        return webService.getData(
            Order.RELEASED.value,
            page.toString()
        ).map { it.results }
    }

    override fun getTopRating(page: Int): Single<List<DataItem>> {
        return webService.getData(
            Order.RATING.value,
            page.toString()
        ).map { it.results }
    }

    override fun search(keyword: String, page: Int): Single<List<DataItem>> {
        return webService.searchData(
            Order.RELEASED.value,
            keyword,
            page.toString()
        ).map { it.results }
    }
}