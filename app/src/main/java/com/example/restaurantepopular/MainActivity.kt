package com.example.restaurantepopular

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.restaurantepopular.ui.componets.banner.Banner
import com.example.restaurantepopular.ui.componets.menu.MenuScreen
import com.example.restaurantepopular.ui.componets.tickets.TicketsScreen
import com.example.restaurantepopular.ui.componets.navBar.NavBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
                    Banner()
                },
                bottomBar = {
                    NavBar(
                        onHome = { /* ação */ },
                        onAvaliar = { /* ação */ },
                        onDados = { /* ação */ }
                    )
                }
            ) { innerPadding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    TicketsScreen()
                    MenuScreen()
                }
            }
        }
    }
}