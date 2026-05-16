package com.example.pasienapiapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pasienapiapp.R
import com.example.pasienapiapp.model.Pasien

class PasienAdapter(
    private val listPasien: List<Pasien>
) : RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    class PasienViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvInfo: TextView = itemView.findViewById(R.id.tvInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pasien, parent, false)

        return PasienViewHolder(view)
    }

    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {

        val pasien = listPasien[position]

        holder.tvNama.text = pasien.nama

        holder.tvInfo.text =
            "JK: ${pasien.jenis_kelamin} | HP: ${pasien.no_telepon}"
    }

    override fun getItemCount(): Int {
        return listPasien.size
    }
}