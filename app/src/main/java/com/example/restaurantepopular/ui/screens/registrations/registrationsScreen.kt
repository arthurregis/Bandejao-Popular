package com.example.restaurantepopular.ui.screens.registrations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantepopular.ui.componets.banner.Banner
import com.example.restaurantepopular.ui.componets.navBar.NavBarAdmin
import com.example.restaurantepopular.ui.screens.cadastroconsumos.CadastroConsumos
import com.example.restaurantepopular.ui.screens.registerMenu.RegisterMenuScreen

@Composable
fun RegistrationsScreen(
    onGerenciar: () -> Unit,
    onNavProfile: () -> Unit,
    onNavHome: () -> Unit
){
    Scaffold(
        topBar = { Banner() },
        bottomBar = {
            NavBarAdmin(
                onHome = onNavHome,
                onGerenciar = onGerenciar,
                onProfile = onNavProfile
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            item {
                RegisterMenuScreen()
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
            item {
                CadastroConsumos()
            }
        }
    }
}