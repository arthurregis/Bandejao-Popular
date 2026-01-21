package com.example.restaurantepopular.ui.screens.registeruser

import androidx.lifecycle.ViewModel
import com.example.restaurantepopular.data.repository.AuthRepository

class RegisterUserViewModel : ViewModel() {

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        AuthRepository.register(
            name = name,
            email = email,
            password = password,
            onSuccess = onSuccess,
            onError = onError
        )
    }
}