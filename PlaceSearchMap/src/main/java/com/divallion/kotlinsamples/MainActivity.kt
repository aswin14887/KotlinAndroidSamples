package com.divallion.kotlinsamples

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val map: SupportMapFragment = mapFragmentView as SupportMapFragment
        map.getMapAsync(this)
        searchView.setOnClickListener{
            try {
                val intent : Intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(this)
                startActivityForResult(intent,PLACE_AUTOCOMPLETE_REQUEST_CODE)
                searchView.visibility = View.INVISIBLE
            }
            catch (e:GooglePlayServicesRepairableException) {
            }
            catch (e:GooglePlayServicesNotAvailableException) {
            }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val place = PlaceAutocomplete.getPlace(this, data)
                updatePlaceOnMap(place)
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Log.e("Error","Place Error"+PlaceAutocomplete.getStatus(this, data).status)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.e("Canceled","Result cancel")
            }
        }
        searchView.visibility = View.VISIBLE
    }

    fun updatePlaceOnMap(place: Place?) {
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(place?.latLng,13f))
        mMap?.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker())
                .title(place?.address as String)
                .position(place.latLng))
    }


}
