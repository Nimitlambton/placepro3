package com.example.labtest1.feeskeeper.myplaces3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.animal_list_item.view.*


class AnimalAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>(){




    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.animal_list_item, parent, false)
        )


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.tvAnimalType?.text = items.get(position)

        holder?.itemView.setOnClickListener{

            addAct.country = items.get(position)

            val ieventreport = Intent(this.context, addAct::class.java)
            this.context.startActivity(ieventreport)


        }


    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to

    val tvAnimalType = view.tv_animal_type

}







