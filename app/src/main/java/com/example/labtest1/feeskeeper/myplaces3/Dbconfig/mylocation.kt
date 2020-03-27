package com.example.labtest1.feeskeeper.myplaces3.Dbconfig


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Location_table")
class mylocation(

   @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "loction_Id") val loction_Id2: Int,


   @ColumnInfo(name="Name") val Name:String,
   @ColumnInfo(name="Date") val Date:String,
   @ColumnInfo(name="country") val country:String,
   @ColumnInfo(name="Gender") val Gender:String,
   @ColumnInfo(name="title") val title1:String,
   @ColumnInfo(name="longitude") val longitude1:Double,
   @ColumnInfo(name="latitude") val latitude1:Double,
   @ColumnInfo(name="img") val img:String,
   @ColumnInfo(name="subtitle") val subtitle1:String)



