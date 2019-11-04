package com.soict.hoangviet.baseproject.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soict.hoangviet.baseproject.R
import com.soict.hoangviet.baseproject.widget.BaseCustomViewRelativeLayout
import kotlinx.android.synthetic.main.layout_base_recycler_view.view.*

class BaseRecyclerView(context: Context?, attrs: AttributeSet?) :
        BaseCustomViewRelativeLayout(context, attrs) {
    private var mBaseAdapter: EndlessLoadingRecyclerViewAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mSwipeRefreshListener: BaseSwipeRefreshListener? = null

    override val layoutRes: Int
        get() = R.layout.layout_base_recycler_view

    override fun initView() {
        swipe_refresh_layout.setColorSchemeResources(R.color.colorPrimary)
    }

    override fun initData() {

    }

    override fun initListener() {
    }

    fun setAdapter(adapter: EndlessLoadingRecyclerViewAdapter) {
        mBaseAdapter = adapter
        recycler_view_actual.adapter = mBaseAdapter
    }

    fun setLinearLayoutManager() {
        mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view_actual.layoutManager = mLayoutManager
        swipe_refresh_layout.setOnRefreshListener {
            mSwipeRefreshListener?.let {
                it.onSwipeRefresh()
            }
        }
    }

    fun setGridLayoutManager(numberColumn: Int) {
        mLayoutManager = GridLayoutManager(context, numberColumn)
        recycler_view_actual.layoutManager = mLayoutManager
        swipe_refresh_layout.setOnRefreshListener {
            mSwipeRefreshListener?.let {
                it.onSwipeRefresh()
            }
        }
    }

    fun setOnRefreshingListener(listener: BaseSwipeRefreshListener) {
        this.mSwipeRefreshListener = listener
    }

    fun showLoadingMoreProgress() {
        mBaseAdapter?.let {
            it.showLoadingMoreProgress()
        }
    }

    fun setIsLoading(isLoading: Boolean) {
        mBaseAdapter?.let {
            it.setIsLoading(isLoading)
        }
    }

    fun hideLoadingMoreProgress() {
        mBaseAdapter?.let {
            it.hideLoadingMoreProgress()
        }
    }

    fun enableLoadingMore(enable: Boolean) {
        mBaseAdapter?.let {
            it.enableLoadingMore(enable)
        }
    }

    fun setLoadingMoreListner(listener: EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener) {
        mBaseAdapter?.let {
            it.setLoadingMoreListner(listener)
        }
    }

    fun clear() {
        mBaseAdapter?.let {
            it.clear()
        }
    }

    fun addModels(listModel: List<Any?>, isScroolToLast: Boolean) {
        mBaseAdapter?.let {
            it.addModels(listModel, isScroolToLast)
        }
    }

    fun hideRefreshing() {
        swipe_refresh_layout.isRefreshing = false
    }

    fun showRefreshing() {
        swipe_refresh_layout.isRefreshing = true
    }


    interface BaseSwipeRefreshListener {
        fun onSwipeRefresh()
    }
}