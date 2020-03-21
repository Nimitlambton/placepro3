package com.example.labtest1.feeskeeper.myplaces3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.feeViewModel
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient


private lateinit var wordViewModel: feeViewModel

  class MapsActivity : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener{

      private lateinit var mMap: GoogleMap
      private lateinit var myMarker :  Marker
      private var PERTH = LatLng(-31.952854, 115.857342)
      private var title = "blue"

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
                for (loca in it){





                    setloc(loca)


                }




            }
        })








    }

    private fun setloc(loca: mylocation) {

        var PERTH = LatLng(loca.latitude1,loca.longitude1)

        var title = loca.title1

        mMap.setOnMarkerClickListener(this);

        myMarker =  mMap.addMarker(MarkerOptions().position(PERTH).title(title))



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
        startActivity(intent)
    }

      override fun onMarkerClick(hel: Marker): Boolean {





          val myToast = Toast.makeText(applicationContext,hel.title+hel.id.toString(),Toast.LENGTH_SHORT)
          myToast.show()
         // gotoupdate()


          return true
      }


      private fun gotoupdate() {
          val intent = Intent(this , updateAct::class.java)
          startActivity(intent)
      }

  }



