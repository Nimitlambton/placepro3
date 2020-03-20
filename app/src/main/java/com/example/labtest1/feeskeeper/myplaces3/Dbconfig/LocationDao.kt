package com.example.labtest1.feeskeeper.myplaces3.Dbconfig

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {

    @Query("SELECT * from Location_table")
    fun getalldata(): LiveData<List<mylocation>>

    //insert all data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(location : mylocation)

    //Delete all data
    @Query("DELETE FROM location_table")
    suspend fun deleteAll()

    @Query("DELETE FROM location_table WHERE loction_Id = :userId")
    suspend fun deleteByUserId(userId: Int)

}