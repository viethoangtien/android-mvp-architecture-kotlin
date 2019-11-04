package com.soict.hoangviet.baseproject.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soict.hoangviet.baseproject.R
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import com.soict.hoangviet.baseproject.extension.inflate
import kotlinx.android.synthetic.main.item_test.view.*

class TestAdapter(context: Context) : EndlessLoadingRecyclerViewAdapter(context) {
    override fun initLoadingViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder {
        return LoadingViewHolder(context.inflate(R.layout.layout_loading))
    }

    override fun onBindLoadingViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun initNormalViewHolder(parent: ViewGroup, viewType: Int): NormalViewHoler? {
        return TestViewHolder(context.inflate(R.layout.item_test, parent, false))
    }

    override fun bindNormalViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItemPosition(position,TestResponse::class.java)
        val testHolder = holder as TestViewHolder
        testHolder.tvStartingPoint.text = data.startPoint
        testHolder.tvEndingPoint.text = data.endPoint
        testHolder.tvNumberSeat.text = data.emptySeat.toString()
        testHolder.tvDistance.text = data.distance.toString()
    }

    class TestViewHolder(itemView: View) : NormalViewHoler(itemView) {
        val tvStartingPoint = itemView.tv_starting_point
        val tvEndingPoint = itemView.tv_ending_point
        val tvNumberSeat = itemView.tv_number_seat
        val tvDistance = itemView.tv_distance
    }

}