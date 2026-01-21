package com.example.restaurantepopular.data.repository

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore

object UserRepository {

    private val db = FirebaseFirestore.getInstance()

    fun saveUser(uid: String, email: String) {
        val user = mapOf(
            "uid" to uid,
            "email" to email,
            "createdAt" to System.currentTimeMillis()
        )

        db.collection("users")
            .document(uid)
            .set(user)
    }
}