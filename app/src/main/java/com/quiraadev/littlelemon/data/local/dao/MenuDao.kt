package com.quiraadev.littlelemon.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.quiraadev.littlelemon.data.local.entities.MenuEntity

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu_item")
    fun getAllMenuItems(): LiveData<List<MenuEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg menuItems: MenuEntity)

    @Query("SELECT (SELECT COUNT(*) FROM menu_item) == 0")
    fun isEmpty(): Boolean
}