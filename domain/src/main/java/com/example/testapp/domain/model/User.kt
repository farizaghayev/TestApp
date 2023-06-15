package com.example.testapp.domain.model


data class User(
    val firstname: String,
    val emailAddress: String,
    val website: String?,
    val photo: String?,
    val password: String
) : java.io.Serializable