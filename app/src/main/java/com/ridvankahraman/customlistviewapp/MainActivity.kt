package com.ridvankahraman.customlistviewapp

import android.app.Notification.EXTRA_PEOPLE
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.Display
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ridvankahraman.customlistviewappdata.Model
import com.ridvankahraman.customlistviewappdata.Person
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://randomuser.me/api/?results=10"
        AsyncTaskHandleJson().execute(url)
    }
    inner class  AsyncTaskHandleJson : AsyncTask<String, String, String>(){
        override fun doInBackground(vararg url: String?): String? {
            val text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use{reader-> reader.readText()} }
            }finally {
                connection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    private fun handleJson(jsonString: String?){
        var listview = findViewById<ListView>(R.id.listView)
        val myClass: Model = Gson().fromJson(jsonString, Model::class.java)
        val outputJson: String = Gson().toJson(myClass)
        listview.adapter = MyAdapter(this, R.layout.row, (myClass.results as ArrayList))
        listview.setOnItemClickListener { adapterView, view, int, log ->
            val intent = Intent(this, ListBar::class.java)
            val person: Person  =  myClass.results[int]
            intent.putExtra("first",person.name.first)
            intent.putExtra("last",person.name.last)
            intent.putExtra("email",person.email)
            intent.putExtra("country",person.location.country)
            intent.putExtra("city",person.location.city)
            intent.putExtra("image",person.picture.medium)
            intent.putExtra("latitude", person.location.coordinates.latitude)
            intent.putExtra("longitude", person.location.coordinates.longitude)
            startActivity(intent)
        }
    }
}