package com.demo.kotlinboot.models

data class Developer(
    val name: String,
    val country: String,
    val website: String = "",
    val details: String = ""
)
