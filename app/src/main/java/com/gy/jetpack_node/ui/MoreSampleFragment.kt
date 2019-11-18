package com.gy.jetpack_node.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gy.jetpack_node.R
import com.gy.jetpack_node.ui.liveData.LiveDataFragment
import com.gy.jetpack_node.ui.viewmodel.ViewModelFragment
import com.gy.jetpack_node.widget.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_more_sample.*
import kotlinx.android.synthetic.main.layout_loading.*

class MoreSampleFragment : Fragment(){

    private val mTitles = arrayOf("LiveData", "ViewModel", "Room", "WorkManager", "Camera2")
    private val mFgs by lazy {
        arrayOf(
                LiveDataFragment(),
                ViewModelFragment(),
                LiveDataFragment(),
                LiveDataFragment(),
                LiveDataFragment()

//                CodeFragment(),
//                CodeFragment(),
//                CodeFragment()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_more_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            setUI()
            loading.visibility = View.GONE
        }, 500)
    }

    private fun setUI() {
        vp.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = mFgs.size
            override fun createFragment(position: Int) = mFgs[position]
        }
        vp.offscreenPageLimit = mTitles.size
        TabLayoutMediator(tab, vp,
                TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                    tab.text = mTitles[position] }).attach()


    }
}