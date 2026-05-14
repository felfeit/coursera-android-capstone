package com.quiraadev.littlelemon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quiraadev.littlelemon.data.local.dao.MenuDao
import com.quiraadev.littlelemon.data.local.entities.MenuEntity

@Database(entities = [MenuEntity::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}