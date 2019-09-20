package com.blurrays.myjobapp

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.blurrays.myjobapp.Classes.Company
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.synthetic.main.activity_company_info.*
import com.google.android.gms.common.api.GoogleApiClient
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.IOException


class CompanyInfoActivity : AppCompatActivity() {

    var position = LatLng( 51.4638009,5.6117138)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_info)

        val mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        var company :Company = Company()


//        with(mapViewCompany) {
//            // Initialise the MapView
//            onCreate(null)
//            // Set the map ready callback to receive the GoogleMap object
//            getMapAsync {
//                MapsInitializer.initialize(applicationContext)
//                setMapLocation(it)
//            }
//        }

        val mapView =  mapViewCompany
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->
            val coordinates = position
            googleMap.addMarker(MarkerOptions().position(coordinates).title("Brandevoortse hoeve"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15f))
            mapView.onResume()
        }




        db.collection("companies").document(intent.getStringExtra("companyId")).get().addOnSuccessListener { documentSnapshot ->
            company = documentSnapshot.toObject(Company::class.java)!!
            textViewCompanyTitleInfo.text = company.name
            textViewCompanyEmailInfo.text = company.email
            textViewCompanyPhoneInfo.text = company.phone
            textViewCompanyAdressInfo.text = company.streetname +" "+ company.housenumber +" "+ company.subpremise + " " + company.city
            textViewCompanyWebsiteInfo.text = company.website
            position = this!!.getLocationFromAddress(this,company.streetname +" "+ company.housenumber +" " + company.city)!!

            mapView.getMapAsync { googleMap ->
                val coordinates = position
                googleMap.addMarker(MarkerOptions().position(coordinates).title("Brandevoortse hoeve"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15f))
                mapView.onResume()
            }
        }




        FABReturnToChoosCompany.setOnClickListener{
            val intent = Intent(this, ChooseCompanyActivity::class.java)
            startActivity(intent)
        }
    }


//    private fun setMapLocation(map : GoogleMap) {
//        with(map) {
//
//            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
//            addMarker(MarkerOptions().position(position))
//            mapType = GoogleMap.MAP_TYPE_NORMAL
//            setOnMapClickListener {
//                Toast.makeText(this@CompanyInfoActivity, "Clicked on map", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    override fun onResume() {
        super.onResume()
        mapViewCompany.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapViewCompany.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapViewCompany.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapViewCompany.onLowMemory()
    }

    private fun getLocationFromAddress(context: Context, strAddress: String): LatLng? {

        val coder = Geocoder(context)
        val address: List<Address>?
        var p1: LatLng? = null

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5)
            if (address == null) {
                return null
            }

            val location = address[0]
            p1 = LatLng(location.latitude, location.longitude)

        } catch (ex: IOException) {

            ex.printStackTrace()
        }

        return p1
    }

}
