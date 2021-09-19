package com.oratakashi.codingtest.domain

import com.oratakashi.codingtest.domain.model.Games
import io.reactivex.Single

interface SearchUsecase {
    fun searchGames(keyword: String, page: Int): Single<List<Games>>
}