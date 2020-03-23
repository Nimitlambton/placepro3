package com.example.labtest1.feeskeeper.myplaces3


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.feeViewModel
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation


private lateinit var wordViewModel: feeViewModel

private  var id = 0
class addAct : AppCompatActivity() {




    companion object {

        var isupdate = false
        var booze = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        var longi = findViewById(R.id.longi) as EditText
        var lati  = findViewById(R.id.lati) as EditText
        var locationsub = findViewById(R.id.subtitle) as EditText
        var locationtitle = findViewById(R.id.title) as EditText
        var save = findViewById(R.id.save) as Button



        if (isupdate) {

            save.setText("Update")
            this.title = "Update"


            wordViewModel = ViewModelProvider(this).get(feeViewModel::class.java)
            wordViewModel.allfee.observe(this, Observer { words ->

                words.let {

                    if(it != null && isupdate){

                        id =  it[booze.toInt()].loction_Id2
                        longi.setText(it[booze.toInt()].latitude1.toString())
                        lati.setText(it[booze.toInt()].longitude1.toString())
                        locationsub.setText(it[booze.toInt()].subtitle1)
                        locationtitle.setText(it[booze.toInt()].title1)


                    }





                }
            })

        }








            save.setOnClickListener {

                wordViewModel = ViewModelProvider(this).get(feeViewModel::class.java)

                if(!isupdate) {

                    if (TextUtils.isEmpty(longi.text) && TextUtils.isEmpty(lati.text) && TextUtils.isEmpty(
                            locationsub.text
                        ) && TextUtils.isEmpty(locationtitle.text)
                    ) {


                    } else {

                        var word2 = mylocation(
                            0,
                            longi.text.toString().toDouble(),
                            lati.text.toString().toDouble(),
                           locationtitle.text.toString(),
                            locationsub.text.toString()
                        )

                        wordViewModel.insert(word2)
                        finish()

                    }

            }else {

                    wordViewModel = ViewModelProvider(this).get(feeViewModel::class.java)
                    var word2 = mylocation(
                        id,
                        longi.text.toString().toDouble(),
                        lati.text.toString().toDouble(),
                        locationtitle.text.toString(),
                        locationsub.text.toString())

                    wordViewModel.update(word2)

                    finish()

                }



            }




        }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds items to the action bar if it is


        if (isupdate) {

            super.onCreateOptionsMenu(menu)
            menuInflater.inflate(R.menu.menu2, menu)
            return true
        }

        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.del) {
            wordViewModel.delete(id)
            isupdate = false
            finish()


        }

        return super.onOptionsItemSelected(item)

    }









    }

