package com.example.restaurantepopular

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.restaurantepopular.ui.navigation.AppNavHost
import com.example.restaurantepopular.ui.navigation.Routes
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val startDestination = Routes.LOGIN

            AppNavHost(
                navController = navController,
                startDestination = startDestination
            )
            
        }
    }
}