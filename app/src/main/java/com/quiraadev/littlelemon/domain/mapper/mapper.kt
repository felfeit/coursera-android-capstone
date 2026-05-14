package com.quiraadev.littlelemon.domain.mapper

import com.quiraadev.littlelemon.data.local.entities.MenuEntity
import com.quiraadev.littlelemon.data.remote.models.MenuResponseItem

fun MenuResponseItem.toMenuEntity() = MenuEntity(id, title, description, price, image, category)