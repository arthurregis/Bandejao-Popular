package com.example.restaurantepopular.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ConsumoRepository {

    private val db = FirebaseFirestore.getInstance()

    /**
     * Salva o Consumo Realizado
     * collection: "consumo" | document: data (ex.: "2025-11-11")
     */
    suspend fun salvarConsumo(
        data: String,
        proteina: String,
        acompanhamento: String,
        guarnicao: String,
        salada: String
    ): Boolean {
        return try {
            val map = hashMapOf(
                "data" to data,
                "proteina" to proteina,
                "acompanhamento" to acompanhamento,
                "guarnicao" to guarnicao,
                "salada" to salada
            )
            db.collection("registros")
                .document(data)
                .collection("consumo")
                .document("registro")
                .set(map)
                .await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Salva o Desperdício
     * collection: "desperdicios" | document: data
     */
    suspend fun salvarDesperdicio(
        data: String,
        proteina: String,
        acompanhamento: String,
        guarnicao: String,
        salada: String
    ): Boolean {
        return try {
            val map = hashMapOf(
                "data" to data,
                "proteina" to proteina,
                "acompanhamento" to acompanhamento,
                "guarnicao" to guarnicao,
                "salada" to salada
            )
            db.collection("registros")
                .document(data)
                .collection("desperdicios")
                .document("registro")
                .set(map)
                .await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Salva o número de Pessoas Não Atendidas
     * collection: "nao_atendidos" | document: data
     */
    suspend fun salvarNaoAtendidos(
        data: String,
        quantidade: String
    ): Boolean {
        return try {
            val map = hashMapOf(
                "data" to data,
                "quantidade" to quantidade
            )
            db.collection("registros")
                .document(data)
                .collection("nao_atendidos")
                .document("registro")
                .set(map)
                .await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}