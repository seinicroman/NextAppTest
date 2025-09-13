package com.example.nextapptest.domain.mapper

import com.example.nextapptest.db.ThemeEntity
import com.example.nextapptest.domain.model.ThemeModel

fun ThemeEntity.toThemeModel() : ThemeModel {
    return ThemeModel(
        id = id,
        imageId = imageId,
        isSelected = isSelected,
        wasUnlocked = wasUnlocked,
    )
}
fun ThemeModel.toThemeEntity() : ThemeEntity{
    return ThemeEntity(
        id = id,
        imageId = imageId,
        isSelected = isSelected,
        wasUnlocked = wasUnlocked,
        baseWasInited = wasAlreadyInited
    )
}