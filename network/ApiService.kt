package com.example.pasienapiapp.network

import com.example.pasienapiapp.model.LoginRequest
import com.example.pasienapiapp.model.LoginResponse
import com.example.pasienapiapp.model.PasienResponse
import com.example.pasienapiapp.model.PasienRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("pasien")
    suspend fun getPasien(
        @Header("Authorization") token: String
    ): Response<PasienResponse>

    @POST("pasien")
    suspend fun tambahPasien(
        @Header("Authorization") token: String,
        @Body request: PasienRequest
    ): Response<PasienResponse>
}