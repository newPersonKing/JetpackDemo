package com.gy.jetpack_node.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * created by ${Hankkin}
 * on 2019-06-17
 */

class DemoViewModel : ViewModel() {

    var time: Long? = null

    var seekbarValue = MutableLiveData<Int>()
}