package com.example.pasienapiapp.model

data class PasienRequest(
    val nama: String,
    val tanggal_lahir: String,
    val jenis_kelamin: String,
    val alamat: String,
    val no_telepon: String
)