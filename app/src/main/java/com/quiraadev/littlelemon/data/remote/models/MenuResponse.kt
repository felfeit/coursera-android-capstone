package com.quiraadev.littlelemon.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuResponse(
    @SerialName("menu")
    val menu: List<MenuResponseItem>
)

