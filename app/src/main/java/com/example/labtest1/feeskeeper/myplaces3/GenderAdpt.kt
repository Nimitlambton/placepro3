package com.example.labtest1.feeskeeper.myplaces3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class genderadpt(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>(){




    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.animal_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.tvAnimalType?.text = items.get(position)

        holder?.itemView.setOnClickListener{

            addAct.gender = items.get(position)

            val ieventreport = Intent(this.context, addAct::class.java)

            (context as Activity).finish()


        }


    }



}


