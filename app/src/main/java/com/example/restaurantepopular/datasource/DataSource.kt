package com.example.restaurantepopular.datasource

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getTickets(): Int {

        return try {
            val doc = db.collection("tickets")
                .document("availableTickets")
                .get()
                .await()

            doc.getLong("value")?.toInt() ?: 5
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    }

    suspend fun updateTickets(newValue: Int) {
        try {
            db.collection("tickets")
                .document("availableTickets")
                .update("value", newValue)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}