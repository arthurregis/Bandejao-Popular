package com.example.restaurantepopular.ui.screens.management

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantepopular.ui.componets.banner.Banner
import com.example.restaurantepopular.ui.componets.navBar.NavBar
import com.example.restaurantepopular.ui.componets.navBar.NavBarAdmin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagementScreen(
    onCadastros: () -> Unit,
    onNavProfile: () -> Unit,
    onNavHome: () -> Unit,
    onConsumo: () -> Unit,
    onGerenciar: () -> Unit,
) {
    Scaffold(
        topBar = {
           Banner()
        },
        bottomBar = {
            NavBarAdmin(
                onHome = onNavHome,
                onGerenciar = onGerenciar,
                onProfile = onNavProfile
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Gerenciamento",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
            )

            ItemLink(label = "Consumo", onClick = onConsumo)
            ItemLink(label = "Cadastros", onClick = onCadastros)
        }
    }
}

@Composable
fun ItemLink(label: String, onClick: () -> Unit) {
    Text(
        text = label,
        color = Color.Black,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(vertical = 4.dp)
            .background(Color(0xFF1E55B2))
            .padding(vertical = 16.dp, horizontal = 20.dp)

    )
}