package com.example.restaurantepopular.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RelatorioRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun buscarPeriodo(
        inicio: String,
        fim: String
    ): Triple<
            List<Map<String, Any>>,
            List<Map<String, Any>>,
            List<Map<String, Any>>
            > {

        val consumo = buscarSubcolecao("consumo", inicio, fim)
        val desperdicios = buscarSubcolecao("desperdicios", inicio, fim)
        val naoAtendidos = buscarSubcolecao("nao_atendidos", inicio, fim)

        return Triple(consumo, desperdicios, naoAtendidos)
    }

    private suspend fun buscarSubcolecao(
        tipo: String,
        inicio: String,
        fim: String
    ): List<Map<String, Any>> {

        val resultado = mutableListOf<Map<String, Any>>()

        val registros = db.collection("registros")
            .whereGreaterThanOrEqualTo("data", inicio)
            .whereLessThanOrEqualTo("data", fim)
            .get()
            .await()

        for (doc in registros.documents) {
            val registro = doc.reference
                .collection(tipo)
                .document("registro")
                .get()
                .await()

            if (registro.exists()) {
                resultado.add(registro.data ?: emptyMap())
            }
        }

        return resultado
    }
}