package com.quiraadev.littlelemon.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MenuResponseItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)