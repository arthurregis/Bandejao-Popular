package com.example.restaurantepopular.ui.componets.menu

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextoItem(texto: String?) {
    Text(
        text = texto?.uppercase() ?: "NÃO INFORMADO",
        fontSize = 22.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}