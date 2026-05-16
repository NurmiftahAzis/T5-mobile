package com.example.pasienapiapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pasienapiapp.model.PasienRequest
import com.example.pasienapiapp.network.RetrofitClient
import kotlinx.coroutines.launch

class TambahPasienActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etTanggalLahir: EditText
    private lateinit var etJenisKelamin: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etNoTelepon: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pasien)

        etNama = findViewById(R.id.etNama)
        etTanggalLahir = findViewById(R.id.etTanggalLahir)
        etJenisKelamin = findViewById(R.id.etJenisKelamin)
        etAlamat = findViewById(R.id.etAlamat)
        etNoTelepon = findViewById(R.id.etNoTelepon)
        btnSimpan = findViewById(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            simpanPasien()
        }
    }

    private fun simpanPasien() {

        val nama = etNama.text.toString().trim()
        val tanggalLahir = etTanggalLahir.text.toString().trim()
        val jenisKelamin = etJenisKelamin.text.toString().trim()
        val alamat = etAlamat.text.toString().trim()
        val noTelepon = etNoTelepon.text.toString().trim()

        if (
            nama.isEmpty() ||
            tanggalLahir.isEmpty() ||
            jenisKelamin.isEmpty() ||
            alamat.isEmpty() ||
            noTelepon.isEmpty()
        ) {

            Toast.makeText(
                this,
                "Semua field wajib diisi",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val sharedPref = getSharedPreferences(
            "LOGIN_PREF",
            MODE_PRIVATE
        )

        val token = sharedPref.getString(
            "TOKEN",
            ""
        ) ?: ""

        lifecycleScope.launch {

            try {

                val request = PasienRequest(
                    nama,
                    tanggalLahir,
                    jenisKelamin,
                    alamat,
                    noTelepon
                )

                val response = RetrofitClient.apiService.tambahPasien(
                    "Bearer $token",
                    request
                )

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@TambahPasienActivity,
                        "Pasien berhasil ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    val errorBody = response.errorBody()?.string()

                    Toast.makeText(
                        this@TambahPasienActivity,
                        "Error: $errorBody",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@TambahPasienActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}