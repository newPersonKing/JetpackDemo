package com.gy.jetpack_node

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gy.jetpack_node.data.respository.HomeRepository
import com.gy.jetpack_node.data.respository.PagingRespository
import com.gy.jetpack_node.data.respository.gank.GankRespository
import com.gy.jetpack_node.ui.home.HomeListViewModel
import com.gy.jetpack_node.ui.liveData.LiveDataViewModel
import com.gy.jetpack_node.ui.paging.PagingWithDaoViewModel
import com.gy.jetpack_node.ui.paging.PagingWithNetWorkViewModel
import com.gy.jetpack_node.utils.Injection

class ViewModelFactory (
        private val homeRepository: HomeRepository,
        private val pagingRespository: PagingRespository,
        private val gankRespository: GankRespository
):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = with(modelClass){
            when{
                isAssignableFrom(HomeListViewModel::class.java)->{
                    HomeListViewModel(homeRepository)
                }
                isAssignableFrom(LiveDataViewModel::class.java)->{
                    LiveDataViewModel()
                }
                isAssignableFrom(PagingWithDaoViewModel::class.java)->{
                    PagingWithDaoViewModel(pagingRespository)
                }
                isAssignableFrom(PagingWithNetWorkViewModel::class.java)->{
                    PagingWithNetWorkViewModel(gankRespository);
                }
                else->{
                    throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
                }
            }
        } as T

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(
                            Injection.provideHomeRepository(application),
                            Injection.providePagingRepository(application),
                            Injection.provideGankRespository()
                    )
                }
    }

}