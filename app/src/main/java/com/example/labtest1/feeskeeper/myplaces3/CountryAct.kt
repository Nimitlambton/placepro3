package com.example.labtest1.feeskeeper.myplaces3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_country.*

class CountryAct : AppCompatActivity(){


    val animals: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        this.title = "select country"

        addcountry()
        recyle.layoutManager = LinearLayoutManager(this)
        recyle.adapter = AnimalAdapter(animals, this)


    }

    private fun addcountry() {

        animals.add("USA")
        animals.add("Canada")
        animals.add("India")
        animals.add("England")
        animals.add("Spain")
        animals.add("Italy")
        animals.add("rome")
        animals.add("Russia")
    }




}
