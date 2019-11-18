package com.gy.jetpack_node.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gy.jetpack_node.R
import com.gy.jetpack_node.databinding.FragmentHomeBinding
import com.gy.jetpack_node.ext.obtainViewModel
import com.gy.jetpack_node.ui.adapter.HomeListAdapter

class HomeFragment : Fragment(){

    private var isLine: Boolean = false
    private lateinit var viewModel: HomeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val databing = FragmentHomeBinding.inflate(layoutInflater,container,false);

        context?:databing.root

        val adapter = HomeListAdapter()
        databing.rvHome.adapter = adapter

        subscribeUi(adapter,databing)

        return databing.root
}

    private fun subscribeUi(adapter: HomeListAdapter, binding: FragmentHomeBinding) {

        viewModel = obtainViewModel(HomeListViewModel::class.java)

        viewModel.listData.observe(viewLifecycleOwner, Observer { data->
            if(data != null){
                adapter.submitList(data)
            }
        })

        binding.fab.setOnClickListener {

            binding.rvHome.layoutManager = if (isLine) GridLayoutManager(context,2) else LinearLayoutManager(context)
            binding.fab.setImageResource(if (isLine) R.mipmap.icon_lin else R.mipmap.icon_grid)
            isLine = !isLine
        }

    }
}