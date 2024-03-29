package com.gy.jetpack_node.ui.liveData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gy.jetpack_node.R
import com.gy.jetpack_node.ext.obtainViewModel
import com.gy.jetpack_node.utils.FloatWindowUtils
import com.yhao.floatwindow.FloatWindow
import kotlinx.android.synthetic.main.fragment_live_data.*

class LiveDataFragment : Fragment(){

    private lateinit var liveData: MutableLiveData<String>
    private var mId = -1

    private val viewModel: LiveDataViewModel by lazy { obtainViewModel(LiveDataViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_live_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        liveData = MutableLiveData()

        btn_observer_data.setOnClickListener {
            if (FloatWindow.get() == null) {
                FloatWindowUtils.init(activity?.application!!)
            }
            FloatWindowUtils.show()

            //创建一个观察者去更新UI
            val statusObserver = Observer<String> { lifeStatus ->
                FloatWindowUtils.addViewContent("LiveData-onChanged: $lifeStatus")
            }
            liveData.observeForever(statusObserver)
        }

        //改变ViewModel中idLiveData中的值
        btn_observer_map.setOnClickListener {
            mId++
            viewModel.id.postValue(mId)
        }
        //当idLiveData变化后，UserBean也会改变且更新Textview的文本
        viewModel.bean.observe(
                this,
                Observer { tv_livedata_map.text = if (it == null) "未查找到User" else "为你查找到的User为：${it.name}" })
    }

    override fun onStart() {
        super.onStart()
        liveData.value = "onStart()"
    }

    override fun onPause() {
        super.onPause()
        liveData.value = "onPause()"
    }

    override fun onStop() {
        super.onStop()
        liveData.value = "onStop()"
    }

    override fun onDestroy() {
        super.onDestroy()
        liveData.value = "onDestroy()"
    }

}