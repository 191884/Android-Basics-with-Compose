package com.snappy.gridapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val num : Int,
    @DrawableRes val pic : Int,
)