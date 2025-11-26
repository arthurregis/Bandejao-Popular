package com.example.restaurantepopular.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CardapioRepository {

    private val db = FirebaseFirestore.getInstance()

    /**
     * Salva um cardápio no Firestore
     *
     * Estrutura:
     *  collection: "cardapios"
     *  document: data (ex.: "2025-11-11")
     *
     * Campos:
     *  - proteina
     *  - acompanhamento
     *  - guarnicao
     *  - salada
     *  - sobremesa
     */
    suspend fun salvarCardapio(
        data: String,
        proteina: String,
        acompanhamento: String,
        guarnicao: String,
        salada: String,
        sobremesa: String
    ): Boolean {
        return try {

            val map = hashMapOf(
                "data" to data,
                "proteina" to proteina,
                "acompanhamento" to acompanhamento,
                "guarnicao" to guarnicao,
                "salada" to salada,
                "sobremesa" to sobremesa
            )

            db.collection("cardapios")
                .document(data)
                .set(map)
                .await()

            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}