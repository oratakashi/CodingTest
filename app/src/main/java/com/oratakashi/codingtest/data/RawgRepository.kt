package com.oratakashi.codingtest.data

import com.oratakashi.codingtest.data.model.DataItem
import io.reactivex.Single

interface RawgRepository {
    fun getLatest(page: Int): Single<List<DataItem>>
    fun getTopRating(page: Int): Single<List<DataItem>>
    fun search(keyword: String, page: Int): Single<List<DataItem>>
}