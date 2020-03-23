package com.example.labtest1.feeskeeper.myplaces3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.feeViewModel
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


private lateinit var wordViewModel: feeViewModel

  class MapsActivity : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener{

      private lateinit var mMap: GoogleMap
      private lateinit var myMarker :  Marker
      private var PERTH = LatLng(-31.952854, 115.857342)
      private var title2 = "blue"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.clear()
         wordViewModel = ViewModelProvider(this).get(feeViewModel::class.java)
        wordViewModel.allfee.observe(this, Observer { words ->

            words?.let {

                mMap.clear()
                for (loca in it)  {

                    val myToast = Toast.makeText(applicationContext,it.size.toString(),Toast.LENGTH_SHORT)
                    myToast.show()


                    setloc(loca,it.indexOf(loca))

                }
            }
        })








    }

    private fun setloc(loca: mylocation ,pos : Int) {

        var PERTH = LatLng(loca.latitude1,loca.longitude1)

        var title2 = loca.title1

        mMap.setOnMarkerClickListener(this);


        myMarker =  mMap.addMarker(MarkerOptions().position(PERTH).title(title2))

        myMarker.tag = pos

         setidd(title2)


        }

      private fun setidd(title: String): String {

          return title2


      }

      private fun getidd(): String {

          return title2

      }






    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds items to the action bar if it is
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.add) {
            ShowaddDetails()
        }


        return super.onOptionsItemSelected(item)
    }

    private fun ShowaddDetails() {
        val intent = Intent(this , addAct::class.java)
        addAct.isupdate = false
        startActivity(intent)
    }

      override fun onMarkerClick(hel: Marker): Boolean {

          val myToast = Toast.makeText(applicationContext,hel.id.toString(),Toast.LENGTH_SHORT)
          myToast.show()
          gotoupdate(hel.tag.toString())


          return true
      }


      private fun gotoupdate(title: String) {
          val intent = Intent(this , addAct::class.java)
         addAct.booze = title
          addAct.isupdate = true
          startActivity(intent)
      }



  }



