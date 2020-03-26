package com.example.labtest1.feeskeeper.myplaces3


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.feeViewModel
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation
import kotlinx.android.synthetic.main.activity_add.*
import java.io.ByteArrayOutputStream



private lateinit var wordViewModel: feeViewModel
private lateinit var e: String
private lateinit var k: Integer

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
        var imgbtn = findViewById(R.id.imgbtn) as Button
   //     var img = findViewById(R.id.hello) as ImageView


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

                        val po =it[booze].img.toString()
                        val k =  Base64.decode(po,Base64.DEFAULT)
                        val image = BitmapFactory.decodeByteArray(k, 0, k.size)
                        hello.setImageBitmap(image)


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
                            e,
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
                        e,
                        locationsub.text.toString())


                    wordViewModel.update(word2)

                    finish()

                }



            }





        imgbtn.setOnClickListener {


            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

             startActivityForResult(i,200)

        }



        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 200){

            var bmp = data?.extras?.get("data") as Bitmap


            hello.setImageBitmap(bmp)

            val byteArrayOutputStream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            e = Base64.encodeToString(byteArray, Base64.DEFAULT)

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

