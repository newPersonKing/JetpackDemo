package com.gy.jetpack_node.ui.paging

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.gy.jetpack_node.R
import com.gy.jetpack_node.data.NetworkState
import com.gy.jetpack_node.databinding.ActivityPagingWithNetWorkBinding
import com.gy.jetpack_node.ext.obtainViewModel
import com.gy.jetpack_node.ext.setLightMode
import com.gy.jetpack_node.ext.setupToolBar
import com.gy.jetpack_node.ui.adapter.PagingWithNetWorkAdapter
import kotlinx.android.synthetic.main.layout_toolbar.*

class PagingWithNetWorkActivity : AppCompatActivity(){

    private lateinit var mViewModel: PagingWithNetWorkViewModel
    private lateinit var mDataBinding: ActivityPagingWithNetWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging_with_net_work)
        setLightMode()
        setupToolBar(toolbar) {
            title = resources.getString(R.string.paging_with_network)
            setDisplayHomeAsUpEnabled(true)
        }
        mViewModel = obtainViewModel(PagingWithNetWorkViewModel::class.java)
        mDataBinding.vm = mViewModel
        mDataBinding.setLifecycleOwner(this)

        val adapter = PagingWithNetWorkAdapter()
        mDataBinding.rvPagingWithNetwork.adapter = adapter
        mDataBinding.vm?.gankList?.observe(this, Observer { adapter.submitList(it) })
        mDataBinding.vm?.refreshState?.observe(this, Observer {
            mDataBinding.rvPagingWithNetwork.post {
                mDataBinding.swipeRefresh.isRefreshing = it == NetworkState.LOADING
            }
        })

        mDataBinding.vm?.netWorkState?.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}