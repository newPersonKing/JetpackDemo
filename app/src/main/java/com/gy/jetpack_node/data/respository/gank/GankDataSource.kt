package com.gy.jetpack_node.data.respository.gank

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gy.jetpack_node.data.NetworkState
import com.gy.jetpack_node.data.bean.Gank
import com.gy.jetpack_node.data.bean.GankResponse
import com.gy.jetpack_node.data.net.Api
import com.gy.jetpack_node.utils.Injection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GankDataSource (private val api: Api = Injection.provideApi()):PageKeyedDataSource<Int,Gank>(){

    private var retry : (()->Any)? = null

    var netWorkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed(){
        val prevRetry = retry
        retry = null
        prevRetry?.also { it.invoke() }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Gank>) {

        initialLoad.postValue(NetworkState.LOADING)
        netWorkState.postValue(NetworkState.HIDDEN)

        Log.i("cccccccccccccc","requestedLoadSize==="+params.requestedLoadSize)
        api.getGank(params.requestedLoadSize,1)
                .enqueue(object : Callback<GankResponse> {
                    override fun onFailure(call: Call<GankResponse>, t: Throwable) {
                        retry = {
                            loadInitial(params,callback)
                        }
                        initialLoad.postValue(NetworkState.FAILED)
                    }

                    override fun onResponse(call: Call<GankResponse>, response: Response<GankResponse>) {
                        if(response.isSuccessful){
                            retry = null
                            callback.onResult(response.body()?.results?: emptyList(),null,2)
                            initialLoad.postValue(NetworkState.LOADED)
                        }else{
                            retry = {
                                loadInitial(params, callback)
                            }
                            initialLoad.postValue(NetworkState.FAILED)
                        }
                    }
                })


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Gank>) {

        Log.i("cccccccccccccc","loadAfter==="+params.requestedLoadSize)
        netWorkState.postValue(NetworkState.LOADING)
        api.getGank(params.requestedLoadSize, params.key)
                .enqueue(object : Callback<GankResponse> {
                    override fun onFailure(call: Call<GankResponse>, t: Throwable) {
                        retry = {
                            loadAfter(params, callback)
                        }
                        netWorkState.postValue(NetworkState.FAILED)
                    }

                    override fun onResponse(call: Call<GankResponse>, response: Response<GankResponse>) {
                        if (response.isSuccessful) {
                            retry = null
                            callback.onResult(
                                    response.body()?.results ?: emptyList(),
                                    params.key + 1
                            )
                            netWorkState.postValue(NetworkState.LOADED)
                        } else {
                            retry = {
                                loadAfter(params, callback)
                            }
                            netWorkState.postValue(NetworkState.FAILED)
                        }
                    }

                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Gank>) {
    }
}