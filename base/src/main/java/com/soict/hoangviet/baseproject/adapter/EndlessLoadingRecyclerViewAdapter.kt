package com.soict.hoangviet.baseproject.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessLoadingRecyclerViewAdapter(context: Context) : RecyclerViewAdapter(context) {
    companion object {
        const val VIEW_TYPE_NORMAL_LOADING = -1
    }

    private var mListener: OnLoadingMoreListener? = null
    private var isLoading: Boolean = false
    private var enableLoadMore: Boolean = false

    override fun solveOnCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        if (viewType == VIEW_TYPE_NORMAL_LOADING) {
            return initLoadingViewHolder(parent, viewType)
        }
        return super.solveOnCreateViewHolder(parent, viewType)
    }

    override fun solveOnBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.solveOnBindViewHolder(holder, position)
        if (mListWrapperModel[position].model == VIEW_TYPE_NORMAL_LOADING) {
            onBindLoadingViewHolder(holder, position)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (isLoading || !enableLoadMore) {
                    return
                }
                val mLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()
                val lastCompleteVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition()
                if (firstVisibleItemPosition > 0 && lastCompleteVisibleItemPosition == itemCount - 1) {
                    isLoading = true
                    mListener?.onLoadMore()
                }
            }
        })
    }

    fun showLoadingMoreProgress() {
        addModel(null, VIEW_TYPE_NORMAL_LOADING, false, true)
    }

    fun hideLoadingMoreProgress() {
        isLoading = false
        removeItemAt(itemCount - 1)
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun enableLoadingMore(enable: Boolean) {
        this.enableLoadMore = enable
    }

    fun setLoadingMoreListner(listener: OnLoadingMoreListener) {
        mListener = listener
    }

    abstract fun initLoadingViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder

    abstract fun onBindLoadingViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnLoadingMoreListener {
        fun onLoadMore()
    }
}