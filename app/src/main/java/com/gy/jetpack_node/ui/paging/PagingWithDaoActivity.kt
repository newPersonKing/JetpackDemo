package com.gy.jetpack_node.ui.paging

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.gy.jetpack_node.R
import com.gy.jetpack_node.data.bean.User
import com.gy.jetpack_node.ext.obtainViewModel
import com.gy.jetpack_node.ext.setLightMode
import com.gy.jetpack_node.ext.setupToolBar
import com.gy.jetpack_node.ui.adapter.PagingDemoAdapter
import kotlinx.android.synthetic.main.activity_paging_with_dao.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PagingWithDaoActivity : AppCompatActivity(){

    private lateinit var viewModel: PagingWithDaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_with_dao)
        setLightMode()

        setupToolBar(toolbar) {
            title = resources.getString(R.string.paging_with_dao)
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel = obtainViewModel(PagingWithDaoViewModel::class.java)

        val adapter = PagingDemoAdapter()
        rv_paging.layoutManager = LinearLayoutManager(this)
        rv_paging.adapter = adapter

        viewModel.allUsers.observe(this,object : Observer<PagedList<User>>{

            override fun onChanged(t: PagedList<User>?) {
                   adapter.submitList(t)
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}

