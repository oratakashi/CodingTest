package com.oratakashi.codingtest.domain

import com.oratakashi.codingtest.data.RawgRepository
import com.oratakashi.codingtest.domain.model.Games
import io.reactivex.Single

class HomeInteractor(
    private val repository: RawgRepository
): HomeUsecase {
    override fun getHeadline(): Single<List<Games>> {
        return repository.getTopRating(1).map { data ->
            data.map { it.toGames() }
        }
    }

    override fun getLatest(): Single<List<Games>> {
        return repository.getLatest(1).map { data ->
            data.map { it.toGames() }
        }
    }
}