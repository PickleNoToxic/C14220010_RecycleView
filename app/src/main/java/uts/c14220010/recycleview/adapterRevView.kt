package uts.c14220010.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.lang.reflect.Array

class adapterRevView (private val listWayang: ArrayList<wayang>) : RecyclerView
.Adapter<adapterRevView.ListViewHolder>(){
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _namaWayang = itemView.findViewById<TextView>(R.id.namaWayang)
        var _karakterWayang = itemView.findViewById<TextView>(R.id.karakterWayang)
        var _deskripsiWayang = itemView.findViewById<TextView>(R.id.deskripsiWayang)
        var _gambarWayang = itemView.findViewById<ImageView>(R.id.gambarWayang)

    }

    private lateinit var onItemClickCallBack: onItemClickCallback

    interface onItemClickCallback {
        fun onItemClicked(data: wayang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var wayang = listWayang[position]
        holder._namaWayang.setText(wayang.nama)
        holder._karakterWayang.setText(wayang.karakter)
        holder._deskripsiWayang.setText(wayang.deskripsi)
        Log.d("gambar", wayang.foto)
        Picasso.get().load(wayang.foto).into(holder._gambarWayang)

        holder._gambarWayang.setOnClickListener {
//            Toast.makeText(holder.itemView.context, wayang.nama, Toast.LENGTH_LONG).show()
            onItemClickCallBack.onItemClicked(listWayang[position])
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: onItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }
}