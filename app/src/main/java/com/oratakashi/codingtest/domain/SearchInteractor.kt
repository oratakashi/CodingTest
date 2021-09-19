package com.oratakashi.codingtest.domain

import com.oratakashi.codingtest.data.RawgRepository
import com.oratakashi.codingtest.domain.model.Games
import io.reactivex.Single

class SearchInteractor(
    private val repository: RawgRepository
): SearchUsecase {
    override fun searchGames(keyword: String, page: Int): Single<List<Games>> {
        return repository.search(keyword, page).map { data ->
            data.map { it.toGames() }
        }
    }
}