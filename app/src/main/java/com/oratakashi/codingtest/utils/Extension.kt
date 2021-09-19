package com.oratakashi.codingtest.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> singleScheduler(
    subscriberScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): SingleSchedulerTransformer<T> {
    return SingleSchedulerTransformer(subscriberScheduler, observerScheduler)
}