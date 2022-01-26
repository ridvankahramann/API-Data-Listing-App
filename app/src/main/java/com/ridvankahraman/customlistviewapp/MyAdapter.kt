package com.ridvankahraman.customlistviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ridvankahraman.customlistviewappdata.Person

class MyAdapter(val context: Context, list1: Int, val list: ArrayList<Person>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(positin: Int): Any {
        return list[positin]
    }

    override fun getItemId(positin: Int): Long {
        return positin.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.row, parent, false)

        val image = view.findViewById(R.id.image) as ImageView
        val name = view.findViewById(R.id.name) as TextView
        val lastname = view.findViewById(R.id.lastname) as TextView
        val country = view.findViewById(R.id.country) as TextView
        val city = view.findViewById(R.id.city) as TextView
        val mail = view.findViewById(R.id.mail) as TextView


        Glide.with(context).load(list[position].picture.medium).into(image)
        name.text = list[position].name.first
        lastname.text = list[position].name.last
        country.text = list[position].location.country
        city.text = list[position].location.city
        mail.text = list[position].email

        return view

    }

}