package com.example.labtest1.feeskeeper.myplaces3.Dbconfig

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LocationDao {

    @Query("SELECT * from Location_table")
    fun getalldata(): LiveData<List<mylocation>>

    //insert all data
    @Insert
    suspend fun insert(location : mylocation)

    //Delete all data
    @Query("DELETE FROM location_table")
    suspend fun deleteAll()


    @Query("DELETE FROM location_table WHERE loction_Id = :userId")
    suspend fun deleteByUserId(userId: Int)


    @Update
    suspend fun update(location : mylocation)


}