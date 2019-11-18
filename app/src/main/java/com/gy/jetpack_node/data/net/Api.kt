package com.gy.jetpack_node.data.net

import com.gy.jetpack_node.data.bean.GankResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface Api{

    @GET("data/Android/{count}/{page}")
    fun getGank(
            @Path(value = "count") count:Int,
            @Path(value = "page")page:Int
    ): Call<GankResponse>
}