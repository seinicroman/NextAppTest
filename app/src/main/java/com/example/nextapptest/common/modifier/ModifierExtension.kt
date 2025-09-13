package com.example.nextapptest.common.modifier

import androidx.compose.ui.Modifier

inline fun Modifier.onCondition(condition: Boolean, block: (Modifier) -> Modifier): Modifier {
    return if (condition) {
        block.invoke(this)
    } else {
        this
    }
}