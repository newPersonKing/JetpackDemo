package com.gy.jetpack_node.utils

import android.content.Context
import com.gy.jetpack_node.data.db.HomeDB
import com.gy.jetpack_node.data.db.UserDB
import com.gy.jetpack_node.data.net.Api
import com.gy.jetpack_node.data.net.HttpClient
import com.gy.jetpack_node.data.respository.HomeRepository
import com.gy.jetpack_node.data.respository.PagingRespository
import com.gy.jetpack_node.data.respository.gank.GankRespository

object Injection {

    fun provideHomeRepository(context: Context) =
            HomeRepository.getInstance(
                    HomeDB.getInstance(context.applicationContext).homeDao()
            )

    fun providePagingRepository(context: Context) =
            PagingRespository.getInstance(
                    UserDB.get(context.applicationContext).userDao()
            )

    fun provideGankRespository() = GankRespository.getInstance()

    fun provideApi(): Api = HttpClient.instance

}