package com.example.labtest1.feeskeeper.myplaces3.Dbconfig

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class

@Database(entities = arrayOf(mylocation::class), version = 2, exportSchema = false)

abstract class LocationDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var fee = database.locationDao()
                    // Delete all content here.
                    fee.deleteAll()
                   // val le = mylocation(0,4.5,6.0,"hellwoorld","thisisworld")
                   //fee.insert(le)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: LocationDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LocationDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocationDatabase::class.java,
                    "location_info"
                ).addCallback(WordDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}