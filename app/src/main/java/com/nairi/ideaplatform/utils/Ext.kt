package com.nairi.ideaplatform.utils

import java.util.Locale

fun Long.toDate() : String {
    val date = java.util.Date(this)
    val format = java.text.SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return format.format(date)

}