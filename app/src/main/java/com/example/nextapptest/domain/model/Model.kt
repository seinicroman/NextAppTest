package com.example.nextapptest.domain.model

data class ThemeModel(
    val id : Int,
    val imageId : Int,
    val isSelected : Boolean,
    val wasUnlocked : Boolean,
    val wasAlreadyInited : Boolean = false
)