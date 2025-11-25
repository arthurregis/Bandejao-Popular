package com.example.restaurantepopular.ui.componets.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Banner() {
    Box(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .fillMaxWidth()
            .background(Color(0xFF1351B4))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "RESTAURANTE POPULAR\nDE QUIXADÁ",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
    }
}