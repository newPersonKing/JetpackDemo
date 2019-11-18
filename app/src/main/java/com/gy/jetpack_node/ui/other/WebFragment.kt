package com.gy.jetpack_node.ui.other

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gy.jetpack_node.R
import com.gy.jetpack_node.databinding.FragmentWebBinding
import com.gy.jetpack_node.ext.clipTxt
import com.gy.jetpack_node.ext.snackBarShow
import com.gy.jetpack_node.utils.CommonUtils
import com.gy.jetpack_node.widget.CoolIndicatorLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.AgentWebSettingsImpl
import com.just.agentweb.DefaultWebClient
import kotlinx.android.synthetic.main.activity_main.*

class WebFragment : Fragment(){

    private var link : String? = ""
    private var title : String? = ""

    private lateinit var binding: FragmentWebBinding

    private lateinit var mAgentWeb: AgentWeb


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /*fragment 使用menu 需要设置此选项*/
        setHasOptionsMenu(true)

        binding = FragmentWebBinding.inflate(layoutInflater,container,false)

        requireActivity().toolbar.title = title

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        link = arguments?.getString("link")
        title = arguments?.getString("title")

        initWeb()
    }

    private fun initWeb() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(binding.webView, LinearLayout.LayoutParams(-1, -1))
                .setCustomIndicator(CoolIndicatorLayout(context))
                .setAgentWebWebSettings(AgentWebSettingsImpl.getInstance())
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .interceptUnkownUrl()
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .go(link)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.web_menu, menu)
        menu.findItem(R.id.action_settings).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> CommonUtils.share(requireActivity(), link!!)
            R.id.action_website -> CommonUtils.openBrowser(requireActivity(),link!!)
            R.id.action_copy -> {
                requireActivity().clipTxt(link!!)
                requireActivity().snackBarShow(binding.llCommonWeb, getString(R.string.clip_hint))
            }
            else -> {
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mAgentWeb.webLifeCycle.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mAgentWeb.webLifeCycle.onResume()
    }

}