package com.gluthfi.uts_berita

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.gluthfi.mpt7.CustomAdapter
import com.gluthfi.mpt7.User
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONObject

class Dashboard : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tambahBtn.setOnClickListener {
            val intent = Intent(this, Insert::class.java)
            startActivity(intent)
            finish()
        }

        button.setOnClickListener{
            val sharedPreferences=getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor=sharedPreferences.edit()

            editor.putString("STATUS","0")
            editor.apply()

            Toast.makeText(applicationContext,"User logout", Toast.LENGTH_LONG).show()

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users=ArrayList<User>()


        AndroidNetworking.get("http://192.168.0.13/uts-berita/berita.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)

                        Log.e("_kotlinTitle", jsonObject.optString("id_berita"))

                        var idB=jsonObject.optString("id_berita").toString()
                        var judulB=jsonObject.optString("judul_berita").toString()
                        var waktuB=jsonObject.optString("waktu_berita").toString()
                        var penulisB=jsonObject.optString("penulis_berita").toString()
                        var isiB=jsonObject.optString("isi_berita").toString()

                        users.add(User("$idB", "$judulB", "$waktuB", "$penulisB", "$isiB"))


                    }

                    val adapter= CustomAdapter(users)
                    recyclerView.adapter=adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })

    }

}