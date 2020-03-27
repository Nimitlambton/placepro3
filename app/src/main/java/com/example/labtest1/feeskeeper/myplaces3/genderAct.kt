package com.example.labtest1.feeskeeper.myplaces3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.activity_gender.*

class genderAct : AppCompatActivity() {

    val animals: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        this.title ="select Gender"
        addcountry()
        abc.layoutManager = LinearLayoutManager(this)
        abc.adapter = genderadpt(animals, this)



    }

    private fun addcountry() {


        animals.add("female")
        animals.add("MAle")
        animals.add("female")
        animals.add("Cant' say")

    }




}
