package com.example.restaurantepopular.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

object FeedbackRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun sendFeedback(
        satisfaction: String,
        suggestion: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        val feedback = mutableMapOf(
            "satisfaction" to satisfaction,
            "createdAt" to FieldValue.serverTimestamp()
        )

        if (suggestion.isNotBlank()) {
            feedback["suggestion"] = suggestion
        }

        firestore.collection("feedbacks")
            .add(feedback)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Erro ao enviar feedback")
            }
    }
}