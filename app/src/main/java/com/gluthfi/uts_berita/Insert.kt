package com.gluthfi.uts_berita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_insert.*
import org.json.JSONArray

class Insert : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        saveBtn.setOnClickListener {
            var data1 = judulInpt.text.toString()
            var data2= waktuInpt.text.toString()
            var data3 = penulisInpt.text.toString()
            var data4 = isiInpt.text.toString()

            postkeserver(data1, data2, data3, data4)

            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        backInsert.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun postkeserver(data1:String, data2:String, data3:String, data4:String) {
        AndroidNetworking.post("http://192.168.0.13/uts-berita/insert.php")
            .addBodyParameter("judul_berita", data1)
            .addBodyParameter("waktu_berita", data2)
            .addBodyParameter("penulis_berita", data3)
            .addBodyParameter("isi_berita", data4)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                    Log.i("Uji Coba", "Sukses")
                    Toast.makeText(applicationContext,"Berhasil insert!", Toast.LENGTH_LONG).show()
                }

                override fun onError(anError: ANError?) {
                    Log.i("Uji Coba", "Mandul")
                }
            })
    }
}
