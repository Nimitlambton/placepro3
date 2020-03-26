package com.example.labtest1.feeskeeper.myplaces3

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation
import kotlinx.android.synthetic.main.activity_add.*


class FeeListAdapter internal constructor(context: Context , var bo : onfeeclick ) : RecyclerView.Adapter<FeeListAdapter.WordViewHolder>() {


    private var feel = emptyList<mylocation>() // Cached copy of words

     class WordViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {

         val wordItemView: TextView = itemView.findViewById(R.id.textView)
         val img :ImageView = itemView.findViewById(R.id.img)

         fun initizliaze(bc: mylocation , action: onfeeclick ){


             wordItemView.text = "Title ="+bc.title1 + "\n " +
                     "Subtitle = " +bc.subtitle1 +
                     "\n latitude ="+ bc.latitude1 +
                     "\nlongitude ="+bc.longitude1 +
                     "\nlongitudeID ="+bc.loction_Id2

             val po =bc.img
             val k =  Base64.decode(po, Base64.DEFAULT)
             val image = BitmapFactory.decodeByteArray(k, 0, k.size)
             img.setImageBitmap(image)





            itemView.setOnClickListener{

                 action.onitemclick(bc,adapterPosition)

             }



         }


    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val current = feel[position]
       holder.initizliaze(current,bo)



    }

    internal fun setWords(fee: List<mylocation>) {
        this.feel = fee
        notifyDataSetChanged()
    }

    override fun getItemCount() = feel.size

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {



         return WordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))



     }



}







interface onfeeclick{

fun  onitemclick(item:mylocation , position: Int)

}


