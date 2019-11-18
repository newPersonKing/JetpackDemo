package com.gy.jetpack_node.ui.viewmodel

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gy.jetpack_node.R
import com.gy.jetpack_node.ext.setLightMode
import com.gy.jetpack_node.ext.setupToolBar
import kotlinx.android.synthetic.main.activity_view_model_share.*

class ViewModelShareActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_share)
        setLightMode()
        setupToolBar(toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}