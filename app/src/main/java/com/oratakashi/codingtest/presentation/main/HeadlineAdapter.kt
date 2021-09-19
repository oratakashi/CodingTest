package com.oratakashi.codingtest.presentation.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.codingtest.databinding.AdapterHeadlineBinding
import com.oratakashi.codingtest.domain.model.Games
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.image.load

class HeadlineAdapter: RecyclerView.Adapter<ViewHolder<AdapterHeadlineBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterHeadlineBinding> {
        return viewBinding(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder<AdapterHeadlineBinding>, position: Int) {
        with(holder.binding){
            ivHeadline.load(data[position].background_image)
            tvHeadline.text = data[position].name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(data: List<Games>){
        this.data.clear()
        this.data.addAll(data.subList(1, 4))
        notifyDataSetChanged()
    }

    private val data: MutableList<Games> by lazy { ArrayList() }
}