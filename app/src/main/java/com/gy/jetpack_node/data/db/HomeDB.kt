package com.gy.jetpack_node.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.gy.jetpack_node.data.Converters
import com.gy.jetpack_node.data.bean.Component
import com.gy.jetpack_node.utils.Constants.DATABASE_NAME
import com.gy.jetpack_node.works.SeedDatabaseWorker

@Database(entities = [Component::class],version = 1,exportSchema = false)
@TypeConverters(Converters::class)/*转换特殊类型*/
abstract class HomeDB : RoomDatabase(){

    abstract fun homeDao():HomeDao

    companion object{

        @Volatile private var instance : HomeDB? = null

        fun getInstance(context: Context) : HomeDB{

            return  instance?: synchronized(this){
                instance?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context):HomeDB{
            return Room.databaseBuilder(context,HomeDB::class.java,DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }).build()
        }
    }

}