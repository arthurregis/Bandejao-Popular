package com.example.restaurantepopular.ui.screens.feedback

import androidx.lifecycle.ViewModel
import com.example.restaurantepopular.data.repository.AuthRepository
import com.google.firebase.firestore.FirebaseFirestore

class FeedbackViewModel : ViewModel() {
    fun sendFeedback(
        satisfaction: String,
        suggestion: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val userId = AuthRepository.getUserId()

        if (userId == null) {
            onError("Usuário não autenticado")
            return
        }

        val feedback = hashMapOf(
            "userId" to userId,
            "satisfaction" to satisfaction,
            "suggestion" to suggestion,
            "createdAt" to System.currentTimeMillis()
        )

        FirebaseFirestore.getInstance()
            .collection("feedbacks")
            .add(feedback)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                onError(it.message ?: "Erro ao salvar feedback")
            }
    }
}