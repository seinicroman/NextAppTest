package com.example.nextapptest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "themes")
data class ThemeEntity(
    @PrimaryKey val id: Int,
    val imageId : Int,
    val wasUnlocked : Boolean,
    val isSelected : Boolean,
    val baseWasInited : Boolean = false
)