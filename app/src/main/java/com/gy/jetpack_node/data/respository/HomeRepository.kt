package com.gy.jetpack_node.data.respository

import com.gy.jetpack_node.data.db.HomeDao

class HomeRepository private constructor(private val homeDao: HomeDao){

    fun getPlants() = homeDao.getComponents();

    companion object{

        // For Singleton instantiation
        @Volatile private var instance: HomeRepository? = null

        fun getInstance(plantDao: HomeDao) =
                instance ?:synchronized(this){
                    instance?: HomeRepository(homeDao = plantDao).also {
                        instance = it
                    }
                }

    }
}