package com.gy.jetpack_node.ui.navigation.fg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gy.jetpack_node.R
import com.gy.jetpack_node.data.bean.Component
import kotlinx.android.synthetic.main.fragment_sample_home.*

class SampleHomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        //jump
        btn_nav_sample_jump.setOnClickListener {
            navController.navigate(R.id.action_homeSampleFragment_to_dashBoardSampleFragment)
        }
        //jump with action
        btn_nav_sample_jump_action.setOnClickListener {
            navController.navigate(R.id.action_homeSampleFragment_to_dashBoardSampleFragment_action)
        }

        val component = Component("1", "我是传递过来实体类Component类型参数", "", "")
        //基本类型跳转
        btn_nav_sample_jump_argument_normal.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("argument_flag",0)
            bundle.putString("argument_normal","基本类型跳转")
            bundle.putSerializable("argument_bean",component)
            navController.navigate(R.id.action_homeSampleFragment_to_argsSampleFragment,bundle)
        }
        //自定义实体类型跳转  注意 实体类需要序列化
        btn_nav_sample_jump_argument_bean.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("argument_flag",1)
            bundle.putString("argument_normal","")
            bundle.putSerializable("argument_bean",component);
            navController.navigate(R.id.action_homeSampleFragment_to_argsSampleFragment,bundle)
        }

    }

}