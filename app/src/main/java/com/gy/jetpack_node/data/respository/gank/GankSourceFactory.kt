package com.gy.jetpack_node.data.respository.gank

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gy.jetpack_node.data.bean.Gank
import com.gy.jetpack_node.data.net.Api
import com.gy.jetpack_node.utils.Injection

/**
 * created by Hankkin
 * on 2019-07-30
 * 干货数据工厂
 */
class GankSourceFactory(private val api: Api = Injection.provideApi()) : DataSource.Factory<Int, Gank>(){

    val sourceLiveData = MutableLiveData<GankDataSource>()

    override fun create(): DataSource<Int, Gank> {
        val source = GankDataSource(api)
        sourceLiveData.postValue(source)
        return  source
    }
}