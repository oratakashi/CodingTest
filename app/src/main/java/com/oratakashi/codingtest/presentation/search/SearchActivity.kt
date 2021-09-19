package com.oratakashi.codingtest.presentation.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.oratakashi.codingtest.R
import com.oratakashi.codingtest.databinding.ActivitySearchBinding
import com.oratakashi.codingtest.utils.VmData
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.showDefaultLayout
import com.oratakashi.viewbinding.core.tools.showErrorLayout
import com.oratakashi.viewbinding.core.tools.showLoadingLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            initObserver()

            rvSearch.also {
                it.adapter = searchAdapter
                it.addItemDecoration(DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL))
            }

            ivBack.onClick { finish() }

            srSearch.setOnRefreshListener {
                srSearch.isRefreshing = false
                viewModel.getSearch(etSearch.text.toString())
            }
        }
    }

    private fun initObserver(){
        with(binding){
            viewModel.setupSearch(etSearch)
            viewModel.search.observe(this@SearchActivity){
                when(it){
                    is VmData.Loading   -> msvSearch.showLoadingLayout()
                    is VmData.Success   -> {
                        msvSearch.showDefaultLayout()
                        searchAdapter.addAll(it.data)
                    }
                    is VmData.Failure   -> {
                        msvSearch.showErrorLayout()
                        it.throwable?.printStackTrace()
                    }
                }
            }
        }
    }

    private val binding: ActivitySearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModel()
}