package com.gy.jetpack_node.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gy.jetpack_node.data.bean.Component

@Dao
interface HomeDao {

    @Query("SELECT * FROM component ORDER BY id")
    fun getComponents(): LiveData<List<Component>>

    /*OnConflictStrategy 冲突策略*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<Component>)
}