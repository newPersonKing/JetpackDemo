package com.gy.jetpack_node.ui.navigation.fg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gy.jetpack_node.R
import com.gy.jetpack_node.data.bean.Component
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sample_args.*

class SampleArgsFragment : Fragment(){

    var compent : Component? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample_args, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        compent = arguments?.getSerializable("argument_bean") as Component

        requireActivity().toolbar.title = resources.getString(R.string.title_args)

        tv_sample_args_content.text = compent!!.title

        btn_nav_args_jump.setOnClickListener {
            findNavController().navigate(R.id.action_argsSampleFragment_to_homeSampleFragment)
        }
    }

}