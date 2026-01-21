package com.example.restaurantepopular.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.restaurantepopular.data.repository.AuthRepository
import com.example.restaurantepopular.domain.session.SessionManager
import com.example.restaurantepopular.domain.session.UserRole
import com.example.restaurantepopular.ui.navigation.HomeResolver

class LoginViewModel : ViewModel() {

    fun login(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onError("Preencha email e senha")
            return
        }

        AuthRepository.login(
            email = email,
            password = password,
            onSuccess = {
                SessionManager.userRole = getRoleByEmail(email)
                onSuccess(HomeResolver.getHomeRoute())
            },
            onError = onError
        )
    }

    private fun getRoleByEmail(email: String): UserRole =
        when {
            email == "admin@gmail.com" -> UserRole.ADMIN
            email.contains("atendente", true) -> UserRole.ATENDENTE
            else -> UserRole.USER
        }
}