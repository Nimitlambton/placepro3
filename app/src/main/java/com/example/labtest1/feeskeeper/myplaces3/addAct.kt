package com.example.labtest1.feeskeeper.myplaces3


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.feeViewModel
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_gender.*
import java.io.ByteArrayOutputStream
import java.util.*


private lateinit var wordViewModel: feeViewModel
private lateinit var e: String
private lateinit var k: Integer

private  var id = 0

class addAct : AppCompatActivity() {

    companion object {

        var isupdate = false
        var booze = 0
        var country = ""
        var gender = ""
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
        var  date = findViewById(R.id.dob) as EditText
        val datePicker = findViewById<DatePicker>(R.id.datePicker1)
        var countrybtn  =  findViewById(R.id.Selectcon) as Button
        var genderbtn  =  findViewById(R.id.Selectgen) as Button
        var countrylabel =  findViewById(R.id.selectc) as TextView
        var genderlabel = findViewById(R.id.mygender) as TextView



        countrylabel.setText(country)
        genderlabel.setText(gender)



        datePicker.visibility = View.INVISIBLE

        date.setOnClickListener{

            closeKeyboard()
            datePicker.visibility = View.VISIBLE

            val today = Calendar.getInstance()
            datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)

            ){
                    view, year, month, day ->
                val month = month + 1
                val msg = "$day/$month/$year"
                System.out.println(msg)
                date.setText(msg)
            }


        }

        countrybtn.setOnClickListener {


            gotocountry()


        }

        genderbtn.setOnClickListener {


            gotogender()
        }

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
                        name.setText(it[booze.toInt()].Name)
                        dob.setText(it[booze.toInt()].Date)
                        countrylabel.setText(it[booze.toInt()].country)
                        genderlabel.setText(it[booze.toInt()].Gender)

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

                    if (TextUtils.isEmpty(longi.text) && TextUtils.isEmpty(lati.text) && TextUtils.isEmpty(locationsub.text) && TextUtils.isEmpty(locationtitle.text) && TextUtils.isEmpty(name.text)   && TextUtils.isEmpty(dob.text)  ) {


                    } else {

                        var word2 = mylocation(
                            0,
                            name.text.toString(),
                            date.text.toString(),
                            country,
                            gender,
                            locationtitle.text.toString(),
                            longi.text.toString().toDouble(),
                            lati.text.toString().toDouble(),
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
                        name.text.toString(),
                        date.text.toString(),
                        country,
                        gender,
                        locationtitle.text.toString(),
                        longi.text.toString().toDouble(),
                        lati.text.toString().toDouble(),
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

    private fun gotogender() {

        val intent = Intent(this, genderAct::class.java)

        startActivity(intent)

    }

    private fun gotocountry() {


        val intent = Intent(this, CountryAct::class.java)

        startActivity(intent)

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
    private fun closeKeyboard() {


        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    }

