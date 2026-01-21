package com.example.restaurantepopular.ui.navigation

import com.example.restaurantepopular.data.repository.AuthRepository

object HomeResolver {

    fun getHomeRoute(): String {
        val email = AuthRepository.getUserEmail() ?: return Routes.LOGIN

        return when {
            email == "admin@gmail.com" -> Routes.HOME_ADMIN
            email.contains("atendente", ignoreCase = true) -> Routes.HOME_ATENDENTE
            else -> Routes.HOME_USER
        }
    }
}