package com.example.labtest1.feeskeeper.myplaces3.Dbconfig

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class feeViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: LocationRepo

    // LiveData gives us updated words when they change.
    val allfee: LiveData<List<mylocation>>

    init {


        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.

        val FeeDao = LocationDatabase.getDatabase(application, viewModelScope).locationDao()

        repository = LocationRepo(FeeDao)

        allfee = repository.allData
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(fee: mylocation) = viewModelScope.launch {

        repository.insert(fee)
    }
//    fun delete(id: Int) = viewModelScope.launch {
//
//        repository.del(id)
//
//    }







}