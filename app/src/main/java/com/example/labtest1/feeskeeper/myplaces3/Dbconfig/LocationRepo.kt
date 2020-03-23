package com.example.labtest1.feeskeeper.myplaces3.Dbconfig

import androidx.lifecycle.LiveData

class LocationRepo(private val feeDao: LocationDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    val allData: LiveData<List<mylocation>> = feeDao.getalldata()

    suspend fun insert(fee: mylocation) {
        feeDao.insert(fee)
    }


    suspend fun update(fee: mylocation) {
        feeDao.update(fee)

    }


    suspend fun del(fee: Int) {

        feeDao.deleteByUserId(fee)
    }





}