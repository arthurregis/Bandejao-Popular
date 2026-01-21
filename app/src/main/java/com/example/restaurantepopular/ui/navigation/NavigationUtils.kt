package com.example.restaurantepopular.ui.navigation

import androidx.navigation.NavController
import com.example.restaurantepopular.domain.session.SessionManager
import com.example.restaurantepopular.domain.session.UserRole

fun navigateToHome(navController: NavController) {
    val route = when (SessionManager.userRole) {
        UserRole.ADMIN -> Routes.HOME_ADMIN
        UserRole.ATENDENTE -> Routes.HOME_ATENDENTE
        UserRole.USER -> Routes.HOME_USER
        null -> Routes.LOGIN
    }

    navController.navigate(route) {
        popUpTo(0)
        launchSingleTop = true
    }
}