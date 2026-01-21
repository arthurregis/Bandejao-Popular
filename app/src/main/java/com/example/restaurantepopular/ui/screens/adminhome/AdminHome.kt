package com.example.restaurantepopular.ui.screens.adminhome

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantepopular.ui.componets.banner.Banner
import com.example.restaurantepopular.ui.componets.menu.MenuCard
import com.example.restaurantepopular.ui.componets.navBar.NavBarAdmin
import com.example.restaurantepopular.ui.componets.tickets.TicketsScreen
import com.example.restaurantepopular.utils.formatarDataParaBR
import com.example.restaurantepopular.viewmodel.CardapioViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminHome(
    onCadastros: () -> Unit,
    onNavProfile: () -> Unit,
    onNavHome: () -> Unit,
    viewModel: CardapioViewModel = viewModel()
){

    val cardapio by viewModel.cardapio.collectAsState()
    val dataSelecionada by viewModel.dataSelecionada.collectAsState()

    Scaffold(
                topBar = {
                    Banner()
                },
                bottomBar = {
                    NavBarAdmin(
                        onHome = onNavHome,
                        onGerenciar = onCadastros,
                        onProfile = onNavProfile
                    )
                }
            ) { innerPadding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    TicketsScreen()
                    MenuCard(
                        data = formatarDataParaBR(dataSelecionada.toString()),
                        cardapio = cardapio,
                        onDiaAnterior = { viewModel.diaAnterior() },
                        onProximoDia = { viewModel.proximoDia() }
                    )
                }
            }
}