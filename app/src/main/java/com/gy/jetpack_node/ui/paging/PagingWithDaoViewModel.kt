package com.gy.jetpack_node.ui.paging

import androidx.lifecycle.ViewModel
import com.gy.jetpack_node.data.bean.User
import com.gy.jetpack_node.data.respository.PagingRespository

class PagingWithDaoViewModel internal constructor(private val pagingRespository: PagingRespository) : ViewModel() {

    val allUsers = pagingRespository.getAllUsers()

    fun insert(text: CharSequence) {
        pagingRespository.insert(text)
    }

    fun remove(user: User) {
        pagingRespository.remove(user)
    }
}
