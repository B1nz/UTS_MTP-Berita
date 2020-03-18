package com.gluthfi.mpt7
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gluthfi.uts_berita.R

class CustomAdapter (val userList: ArrayList<User>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user: User=userList[position]
        holder?.idB?.text = user.id
        holder?.judulB?.text = user.judul
        holder?.waktuB?.text = user.waktu
        holder?.penulisB?.text = user.penulis
        holder?.isiB?.text = user.isi
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return  ViewHolder(v)

    }


    override fun getItemCount(): Int {

        return userList.size
    }



    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val idB = itemView.findViewById(R.id.idB) as TextView
        val judulB = itemView.findViewById(R.id.judulB) as TextView
        val waktuB = itemView.findViewById(R.id.waktuB) as TextView
        val penulisB = itemView.findViewById(R.id.penulisB) as TextView
        val isiB = itemView.findViewById(R.id.isiB) as TextView

    }

}