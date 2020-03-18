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
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var placesClient: PlacesClient

    private val PERTH = LatLng(-31.952854, 115.857342)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        setupLocationClient()
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.clear()


        getCurrentLocation()
        setupPlacesClient()



        mMap.setOnPoiClickListener{


            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()


            displayPoi(it)


        }



        mMap.addMarker(

            MarkerOptions().position(LatLng(20.0, 40.0)).title("Hello world"))

        mMap.addMarker(
            MarkerOptions()
                .position(PERTH)
                .title("Hello world"))



    }


    private fun displayPoi(pointOfInterest: PointOfInterest) {

        // 1
        val placeId = pointOfInterest.placeId
// 2
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.PHONE_NUMBER, Place.Field.PHOTO_METADATAS, Place.Field.ADDRESS, Place.Field.LAT_LNG)
// 3
        val request = FetchPlaceRequest .builder(placeId, placeFields) .build()
// 4
        placesClient.fetchPlace(request) .addOnSuccessListener { response ->
            // 5
            val place = response.place
            Toast.makeText(this, "${place.name}, " + "${place.phoneNumber}", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { exception ->
            // 6
            if (exception is ApiException) {
                val statusCode = exception.statusCode
                Log.e(TAG,"Place not found: " + exception.message + ", " + "statusCode: " + statusCode)
            } }
    }



    private fun setupPlacesClient() {
        Places.initialize(getApplicationContext(), "AIzaSyCvXjqROQVQOo4Nvg4q2L5B8RY0hqh867Q");
        placesClient = Places.createClient(this);
    }



    private fun setupLocationClient() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    private fun requestLocationPermissions() {


        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)

    }

    companion object {
        private const val REQUEST_LOCATION = 1
        private const val TAG = "MapsActivity"
    }


    private fun getCurrentLocation() {
        mMap.isMyLocationEnabled = true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.size == 1 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) { getCurrentLocation()
            } else {
                Log.e(TAG, "Location permission denied")
            }
        }
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
}



