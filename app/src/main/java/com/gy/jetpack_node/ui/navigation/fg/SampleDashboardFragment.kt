package com.gy.jetpack_node.ui.navigation.fg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gy.jetpack_node.R
import kotlinx.android.synthetic.main.fragment_sample_dashboard.*

class SampleDashboardFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_nav_dashboard_jump.setOnClickListener {
            findNavController().navigate(R.id.action_dashBoardSampleFragment_to_notificationSampleFragment)
        }
    }

}