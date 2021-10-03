package dev.leonardom.financeappui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Stock(
    val company: String,
    @DrawableRes val icon: Int,
    val color: Color,
    val price: Double,
    val date: Long
)
