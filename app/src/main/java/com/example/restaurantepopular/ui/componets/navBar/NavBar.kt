package com.example.restaurantepopular.ui.componets.navBar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavBar(
    onHome: () -> Unit,
    onAvaliar: () -> Unit,
    onDados: () -> Unit
) {
    NavigationBar(containerColor = Color(0xFF0A57C8)) {

        NavigationBarItem(
            selected = false,
            onClick = onAvaliar,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White
                )
            },
            label = {
                Text(
                    "AVALIAR",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            },
        )

        NavigationBarItem(
            selected = false,
            onClick = onHome,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White
                )
            },
            label = {
                Text(
                    "INICIAL",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            },
        )

        NavigationBarItem(
            selected = false,
            onClick = onDados,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White
                )
            },
            label = {
                Text(
                    "MEUS DADOS",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            },
        )
    }
}