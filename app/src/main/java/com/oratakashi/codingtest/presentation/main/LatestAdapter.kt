package com.oratakashi.codingtest.presentation.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.codingtest.databinding.AdapterLastestBinding
import com.oratakashi.codingtest.domain.model.Games
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.image.load

class LatestAdapter : RecyclerView.Adapter<ViewHolder<AdapterLastestBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterLastestBinding> {
        return viewBinding(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder<AdapterLastestBinding>, position: Int) {
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