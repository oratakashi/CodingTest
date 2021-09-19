package com.oratakashi.codingtest.domain

import com.oratakashi.codingtest.domain.model.Games
import io.reactivex.Single

interface HomeUsecase {
    fun getHeadline(): Single<List<Games>>
    fun getLatest(): Single<List<Games>>
}