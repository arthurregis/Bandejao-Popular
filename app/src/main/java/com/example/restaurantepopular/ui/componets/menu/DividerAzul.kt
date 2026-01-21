package com.example.restaurantepopular.ui.componets.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DividerAzul() {
    HorizontalDivider(
        color = Color(0xFF0A57C8),
        thickness = 2.dp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}