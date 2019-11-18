package com.gy.jetpack_node.works

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.gy.jetpack_node.data.bean.Component
import com.gy.jetpack_node.data.db.HomeDB
import com.gy.jetpack_node.utils.Constants.COMPONENT_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker (context: Context,workerParameters: WorkerParameters):
        CoroutineWorker(context,workerParameters){

    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope{
       try {
           applicationContext.assets.open(COMPONENT_DATA_FILENAME).use {inputStream->
               JsonReader(inputStream.reader()).use { jsonReader->
                   val plantType = object : TypeToken<List<Component>>(){}.type
                   val plantList: List<Component> = Gson().fromJson(jsonReader,plantType)
                   val database = HomeDB.getInstance(applicationContext)
                   database.homeDao().insertAll(plantList)
               }
           }

           Result.success()
       }catch (e:Exception){
           Log.e(TAG, "Error seeding database", e)
           Result.failure()
       }
    }

}

