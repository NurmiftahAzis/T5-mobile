package com.example.pasienapiapp.model

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData?
)