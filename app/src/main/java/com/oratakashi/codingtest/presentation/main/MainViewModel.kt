package com.oratakashi.codingtest.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.codingtest.domain.HomeUsecase
import com.oratakashi.codingtest.domain.model.Games
import com.oratakashi.codingtest.utils.VmData
import com.oratakashi.codingtest.utils.singleScheduler
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val usecase: HomeUsecase,
    private val disposable: CompositeDisposable
): ViewModel() {
    private val _topRating: MutableLiveData<VmData<List<Games>>> by liveData(VmData.default())
    val topRating: LiveData<VmData<List<Games>>> = _topRating

    private val _latest: MutableLiveData<VmData<List<Games>>> by liveData(VmData.default())
    val latest: LiveData<VmData<List<Games>>> = _latest

    private val _state: MutableLiveData<VmData<Boolean>> by liveData(VmData.default())
    val state: LiveData<VmData<Boolean>> = _state

    fun getHome(){
        _state.postValue(VmData.loading())
        Single.zip(
            usecase.getHeadline(),
            usecase.getLatest(),
            { dataHeadline, dataLatest ->
                _latest.postValue(VmData.success(dataLatest))
                _topRating.postValue(VmData.success(dataHeadline))
                true
            }
        ).compose(singleScheduler()).subscribe({
            _state.postValue(VmData.success(it))
        },{
            it.printStackTrace()
            _state.postValue(VmData.fail(it, it.message))
        }).let { return@let disposable::add }
    }
}