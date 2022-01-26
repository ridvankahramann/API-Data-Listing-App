package com.ridvankahraman.customlistviewapp

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ridvankahraman.customlistviewappdata.Model
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
    }
}