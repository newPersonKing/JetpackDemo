package com.gy.jetpack_node.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gy.jetpack_node.R
import com.gy.jetpack_node.databinding.ActivityMainBinding
import com.gy.jetpack_node.utils.StatusBarUtil
import com.tbruyelle.rxpermissions2.RxPermissions


class FirstActivity : AppCompatActivity(){

    private lateinit var mDataBinding:ActivityMainBinding;
    private lateinit var drawerLayout : DrawerLayout;
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setLightMode(this);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = mDataBinding.drawerLayout;

        mDataBinding.toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.black))

        navController = Navigation.findNavController(this,R.id.fragment_home)

        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.homeFragment,
                        R.id.pagingDemoFragment,
                        R.id.navigationFragment,
                        R.id.lifecyclesFragment
                ),drawerLayout
        )

        setSupportActionBar(mDataBinding.toolbar)

        /*这里是为了同步actionBar上的显示信息 与页面相同*/
        setupActionBarWithNavController(navController,appBarConfiguration)

        mDataBinding.navView.setupWithNavController(navController)

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                requestPermission()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        return return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @SuppressLint("CheckResult")
    private fun requestPermission() {
        RxPermissions(this)
                .requestEach(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .subscribe { p0 ->
                    when {
                        p0.granted -> {
                        }
                        p0.shouldShowRequestPermissionRationale -> {
                        }
                    }
                }
    }
}