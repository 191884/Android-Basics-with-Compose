package com.snappy.affirmationsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val stringRes: Int,
    @DrawableRes val drawableRes: Int
)