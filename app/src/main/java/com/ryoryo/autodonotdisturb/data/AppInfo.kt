package com.ryoryo.autodonotdisturb.data

import android.graphics.drawable.Drawable

data class AppInfo(
    val isSelected: Boolean,
    val name: String,
    val icon: Drawable?,
    val packageName: String
    )