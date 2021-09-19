package com.oratakashi.codingtest.presentation.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.codingtest.databinding.AdapterSearchBinding
import com.oratakashi.codingtest.domain.model.Games
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.image.load

class SearchAdapter: RecyclerView.Adapter<ViewHolder<AdapterSearchBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterSearchBinding> {
        return viewBinding(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder<AdapterSearchBinding>, position: Int) {
        with(holder.binding) {
            ivImage.load(data[position].background_image)
            tvTitle.text = data[position].name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(data: List<Games>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<Games> by lazy { ArrayList() }
}