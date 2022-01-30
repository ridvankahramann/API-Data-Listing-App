package com.ridvankahraman.customlistviewapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ListBar : AppCompatActivity(), OnMapReadyCallback  {
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_bar)
        val first = intent.getStringExtra("first")
        val last = intent.getStringExtra("last")
        val country = intent.getStringExtra("country")
        val city = intent.getStringExtra("city")
        val email = intent.getStringExtra("email")
        val medium = intent.getStringExtra("image")

        var nametext = findViewById<TextView>(R.id.nametext)
        nametext.text = first
        var lasttext = findViewById<TextView>(R.id.lasttext)
        lasttext.text = last
        var countrytext = findViewById<TextView>(R.id.countrytext)
        countrytext.text = country
        var citytext = findViewById<TextView>(R.id.citytext)
        citytext.text = city
        var emailtext = findViewById<TextView>(R.id.emailtext)
        emailtext.text = email
        var imagetext = findViewById<ImageView>(R.id.imagetext)
        Glide.with(imagetext).load(medium).into(imagetext)

        var backbutton = findViewById<Button>(R.id.backbutton)
        backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latitude: Double = intent.getStringExtra("latitude")?.toDouble() ?: 0.0
        val longitude: Double = intent.getStringExtra("longitude")?.toDouble() ?: 0.0
        val sydney = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title("Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}