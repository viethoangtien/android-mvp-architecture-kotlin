package com.soict.hoangviet.baseproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soict.hoangviet.baseproject.adapter.BaseRecyclerView
import com.soict.hoangviet.baseproject.adapter.TestAdapter
import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.api.ApiException
import com.soict.hoangviet.baseproject.data.network.api.RetrofitManager
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import com.soict.hoangviet.baseproject.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_test.*

class MainActivity : AppCompatActivity(), BaseRecyclerView.BaseSwipeRefreshListener {
    private var mTestAdapter: TestAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initAdapter()
        fetchListDriver()
    }

    private fun initAdapter() {
        mTestAdapter = TestAdapter(this)
        base_recycler_view.setAdapter(mTestAdapter!!)
        base_recycler_view.setOnRefreshingListener(this)
        base_recycler_view.setLinearLayoutManager()
    }

    private fun fetchListDriver() {
        val data: MutableMap<String, Any> = mutableMapOf()
        data["status"] = ""
        data["empty_seat"] = ""
        data["start_point"] = ""
        data["start_time"] = ""
        data["end_point"] = ""
        data["limit"] = 6
        data["page"] = 1
        RetrofitManager.getInstance()
            .getListDriverPost(2, data, object : ICallBack<BaseListEntityResponse<TestResponse>> {
                override fun onSuccess(result: BaseListEntityResponse<TestResponse>) {
                    base_recycler_view?.clear()
                    base_recycler_view?.addModels(result?.data!!, false)
                    base_recycler_view?.hideRefreshing()
                }

                override fun onError(e: ApiException) {
                    ToastUtil.show("Error Happen. Please try again!")
                }

            })
    }

    override fun onSwipeRefresh() {
        fetchListDriver()
    }
}