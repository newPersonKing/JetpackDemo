package com.gy.jetpack_node.ui.liveData

import androidx.arch.core.util.Function
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.gy.jetpack_node.data.bean.User

class LiveDataViewModel : ViewModel(){

    val data = listOf(
            User(0, "Hankkin"),
            User(1, "Tony"),
            User(2, "Bob"),
            User(3, "Lucy")
    )

    val id = MutableLiveData<Int>()

    val bean = map(id, Function<Int,User>{
        return@Function findUserById(id.value!!)
    })

    private fun findUserById(id:Int):User?{
        return data.find {
            it.id == id
        }
    }

}