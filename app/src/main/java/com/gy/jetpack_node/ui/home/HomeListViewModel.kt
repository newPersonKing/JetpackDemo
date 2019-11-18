package com.gy.jetpack_node.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gy.jetpack_node.data.bean.Component
import com.gy.jetpack_node.data.respository.HomeRepository

/*internal 限制访问范围*/
class HomeListViewModel internal constructor(homeRepository: HomeRepository):ViewModel(){

    val listData: LiveData<List<Component>> = homeRepository.getPlants()

}