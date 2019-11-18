package com.gy.jetpack_node.ui.navigation.fg

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gy.jetpack_node.R
import com.gy.jetpack_node.ext.snackBarShow
import kotlinx.android.synthetic.main.fragment_sample_notification.*

class SampleNotificationFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample_notification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var deep_link = arguments?.getString("deep_args");
        et_deep_link.setText(deep_link)

        btn_nav_dashboard_jump_home.setOnClickListener {
            findNavController().navigate(R.id.action_notificationSampleFragment_to_homeSampleFragment)
        }

        btn_nav_deep_link.setOnClickListener {
            val args = Bundle()
            args.putString("deep_args","123123131231232")
            /*创建pendingIntent*/
            val deep = findNavController().createDeepLink()
                    .setDestination(R.id.notificationSampleFragment)
                    .setArguments(args)
                    .createPendingIntent()

            val notificationManager =
                    context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                        NotificationChannel(
                                "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH)
                )
            }

            val builder = NotificationCompat.Builder(
                    context!!, "deeplink")
                    .setContentTitle(resources.getString(R.string.app_name))
                    .setContentText("Navigation 深层链接测试")
                    .setSmallIcon(R.mipmap.jetpack)
                    .setContentIntent(deep)
                    .setAutoCancel(true)
            notificationManager.notify(0, builder.build())

            requireActivity().snackBarShow(view,getString(R.string.deeplink_hint))
        }
    }

}