package com.soict.hoangviet.baseproject.ui.view.impl

import com.soict.hoangviet.baseproject.R
import com.soict.hoangviet.baseproject.adapter.BaseRecyclerView
import com.soict.hoangviet.baseproject.adapter.EndlessLoadingRecyclerViewAdapter
import com.soict.hoangviet.baseproject.adapter.TestAdapter
import com.soict.hoangviet.baseproject.ui.presenter.MainPresenter
import com.soict.hoangviet.baseproject.ui.view.MainView
import kotlinx.android.synthetic.main.activity_test.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, BaseRecyclerView.BaseSwipeRefreshListener,
    EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener {

    @Inject
    lateinit var mPresenter: MainPresenter

    private var mTestAdapter: TestAdapter? = null
    private var isLoading = false
    override val mLayoutRes: Int
        get() = R.layout.activity_test
    override val mOnAttachListener: (() -> Unit)?
        get() = {}
    override val mOnDetachListener: ((String) -> Unit)?
        get() = {}

    override fun initView() {
        mPresenter.onAttach(this)
        initAdapter()
        mPresenter.fetchListDriver()
    }

    private fun initAdapter() {
        mTestAdapter = TestAdapter(this)
        base_recycler_view.setAdapter(mTestAdapter!!)
        base_recycler_view.setOnRefreshingListener(this)
        base_recycler_view.setOnLoadMoreListener(this)
        base_recycler_view.setLinearLayoutManager()
    }

    override fun initListener() {
    }

    override fun onSwipeRefresh() {
        mPresenter.fetchListDriver()
    }

    override fun onLoadMore() {
        base_recycler_view.showLoadingMoreProgress()
        isLoading = true
        mPresenter.fetchListDriver()
    }
}


