package com.soict.hoangviet.baseproject.adapter

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallBack(
    val lastModel: MutableList<RecyclerViewAdapter.WrapperModel>,
    val newModel: MutableList<RecyclerViewAdapter.WrapperModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return lastModel.size
    }

    override fun getNewListSize(): Int {
        return newModel.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return lastModel.get(oldItemPosition).id == newModel.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return lastModel[oldItemPosition] == newModel[newItemPosition]
    }
}