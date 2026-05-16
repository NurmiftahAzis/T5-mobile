package com.example.pasienapiapp

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.pasienapiapp.adapter.PasienAdapter
import com.example.pasienapiapp.network.RetrofitClient
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import android.content.Intent

class HomeActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var rvPasien: RecyclerView
    private lateinit var btnTambah: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tvWelcome = findViewById(R.id.tvWelcome)
        rvPasien = findViewById(R.id.rvPasien)

        rvPasien.layoutManager =
            LinearLayoutManager(this)

        val name = intent.getStringExtra("NAME")
        tvWelcome.text = "Selamat Datang, $name"

        getDataPasien()

        btnTambah = findViewById(R.id.btnTambah)

        btnTambah.setOnClickListener {
            startActivity(
                Intent(this, TambahPasienActivity::class.java)
            )
        }
    }

    private fun getDataPasien() {

        val sharedPref = getSharedPreferences("LOGIN_PREF", MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN", "") ?: ""

        lifecycleScope.launch {

            try {

                val response = RetrofitClient.apiService
                    .getPasien("Bearer $token")

                if (response.isSuccessful) {

                    val pasienList = response.body()?.data ?: emptyList()

                    val adapter = PasienAdapter(pasienList)

                    rvPasien.adapter = adapter

                } else {

                    Toast.makeText(
                        this@HomeActivity,
                        "Gagal mengambil data pasien",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@HomeActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}