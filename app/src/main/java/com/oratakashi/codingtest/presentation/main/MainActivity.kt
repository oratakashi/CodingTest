package com.oratakashi.codingtest.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.codingtest.R
import com.oratakashi.codingtest.databinding.ActivityMainBinding
import com.oratakashi.codingtest.presentation.search.SearchActivity
import com.oratakashi.codingtest.utils.VmData
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.image.load
import com.oratakashi.viewbinding.core.tools.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val headlineAdapter: HeadlineAdapter by lazy{
        HeadlineAdapter()
    }

    private val latestAdapter: LatestAdapter by lazy {
        LatestAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            initObserver()

            rvHeadline.also {
                it.adapter = headlineAdapter
                it.layoutManager = GridLayoutManager(applicationContext, 3)
            }

            rvLatest.also {
                it.adapter = latestAdapter
                it.layoutManager = LinearLayoutManager(applicationContext)
                it.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            }

            srMain.setOnRefreshListener {
                srMain.isRefreshing = false
                viewModel.getHome()
            }

            fab.setOnClickListener {
                startActivity(SearchActivity::class.java)
            }

            viewModel.getHome()
        }
    }

    private fun initObserver(){
        with(binding){
            viewModel.latest.observe(this@MainActivity){
                when(it){
                    is VmData.Success -> {
                        latestAdapter.addAll(it.data)
                    }
                }
            }

            viewModel.topRating.observe(this@MainActivity){
                when(it){
                    is VmData.Success -> {
                        if(it.data.isNotEmpty()){
                            val headline = it.data.first()
                            ivHeadline.load(headline.background_image)
                            tvHeadline.text = headline.name
                            headlineAdapter.addAll(it.data.take(4))
                        }
                    }
                }
            }

            viewModel.state.observe(this@MainActivity){
                when(it){
                    is VmData.Loading -> msvMain.showLoadingLayout()
                    is VmData.Success -> msvMain.showDefaultLayout()
                    is VmData.Failure -> {
                        msvMain.showErrorLayout()
                        toast("${it.message}")
                        it.throwable?.printStackTrace()
                    }
                }
            }
        }
    }

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()
}