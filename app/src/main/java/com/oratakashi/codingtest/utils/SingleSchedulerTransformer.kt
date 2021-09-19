package com.oratakashi.codingtest.utils

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SingleSchedulerTransformer<T>(
    private val subscriberScheduler: Scheduler = Schedulers.io(),
    private val observerScheduler: Scheduler = AndroidSchedulers.mainThread()
) : SingleTransformer<T, T> {

    /**
     * Method to apply scheduler
     * @param upstream the completable to be applied
     */
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
    }
}