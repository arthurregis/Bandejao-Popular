package com.example.restaurantepopular.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

object AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d("AUTH", "Login OK UID: ${auth.currentUser?.uid}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("AUTH", "Erro login", e)
                onError(e.message ?: "Erro ao fazer login")
            }
    }

    fun register(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AUTH", "Usuário criado: ${auth.currentUser?.uid}")
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Erro ao cadastrar")
                }
            }
    }

    fun getUserEmail(): String? {
        return auth.currentUser?.email
    }

    fun logout() {
        auth.signOut()
    }

    fun isLogged(): Boolean {
        return auth.currentUser != null
    }

    fun getUserId(): String? {
        return auth.currentUser?.uid
    }
}