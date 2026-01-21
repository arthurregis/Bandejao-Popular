package com.example.restaurantepopular.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatarDataParaBR(dataIso: String): String {
    val data = LocalDate.parse(dataIso) // yyyy-MM-dd
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return data.format(formatter)
}