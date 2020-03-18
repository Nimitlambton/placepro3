//package com.example.labtest1.feeskeeper.myplaces3.Dbconfig
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface LocationDao {
//    //get all data
//    @Query("SELECT * from Location_table")
//    fun getalldata(): LiveData<List<Locations>>
//
//    //insert all data
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(location : Locations)
//
//    //Delete all data
//    @Query("DELETE FROM Location_table")
//    suspend fun deleteAll()
//
//    @Query("DELETE FROM Location_table WHERE loction_Id = :loction_Id")
//    suspend fun deleteByUserId(loction_Id: Int)
//
//
//}