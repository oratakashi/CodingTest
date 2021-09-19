package com.oratakashi.codingtest.presentation.search

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.oratakashi.codingtest.domain.SearchUsecase
import com.oratakashi.codingtest.domain.model.Games
import com.oratakashi.codingtest.utils.VmData
import com.oratakashi.codingtest.utils.singleScheduler
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchViewModel(
    private val usecase: SearchUsecase,
    private val disposable: CompositeDisposable
): ViewModel() {
    private val _search: MutableLiveData<VmData<List<Games>>> by liveData(VmData.default())
    val search: LiveData<VmData<List<Games>>> = _search

    fun getSearch(keyword: String){
        _search.postValue(VmData.loading())
        usecase.searchGames(keyword, 1).compose(singleScheduler()).subscribe({
            _search.postValue(VmData.success(it))
        },{
            _search.postValue(VmData.fail(it, it.message))
        }).let { return@let disposable::add }
    }

    fun setupSearch(editText: EditText){
        editText.textChangeEvents()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>(){
                override fun onNext(t: TextViewTextChangeEvent) {
                    val keyword = t.text.trim().toString()
                    if(keyword.isNotBlank() && keyword.isNotEmpty() && keyword.length >= 3){
                        getSearch(keyword)
                    }
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            }).let { return@let disposable }
    }
}