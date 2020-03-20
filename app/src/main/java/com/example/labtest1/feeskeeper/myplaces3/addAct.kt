package com.example.labtest1.feeskeeper.myplaces3


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.feeViewModel
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation
import kotlinx.android.synthetic.main.activity_add.*



private lateinit var wordViewModel: feeViewModel


class addAct : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)



        var longi = findViewById(R.id.longi) as EditText
        var lati  = findViewById(R.id.lati) as EditText
        var locationsub = findViewById(R.id.subtitle) as EditText
        var locationtitle = findViewById(R.id.title) as EditText
        var save = findViewById(R.id.save) as Button



        wordViewModel = ViewModelProvider(this).get(feeViewModel::class.java)


        save.setOnClickListener {


            val replyIntent = Intent()

            if (TextUtils.isEmpty(longi.text) && TextUtils.isEmpty(lati.text) && TextUtils.isEmpty(locationsub.text) && TextUtils.isEmpty(locationtitle.text)  ) {

             val tu =  Toast.makeText(applicationContext, "fuck",Toast.LENGTH_LONG)
              tu.show()




            } else {


                var word2 = mylocation(0,longi.text.toString().toDouble(),lati.text.toString().toDouble(),subtitle.text.toString(),locationtitle.toString())

            wordViewModel.insert(word2)
                finish()

            }

        }






        }




    }

