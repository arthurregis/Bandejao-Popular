package com.example.restaurantepopular.ui.componets.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecaoTitulo(titulo: String) {
    Text(
        text = "$titulo:",
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}