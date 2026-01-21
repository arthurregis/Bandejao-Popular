package com.example.restaurantepopular.ui.screens.reports

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantepopular.data.RelatorioRepository
import com.example.restaurantepopular.ui.componets.pdfgenerator.PdfGenerator
import kotlinx.coroutines.launch

class ReportsViewModel : ViewModel() {

    private val repo = RelatorioRepository()


    fun gerarPdf(
        context: Context,
        inicio: String,
        fim: String,
        tipos: List<String>
    ) {
        if (inicio.isBlank() || fim.isBlank()) {
            Log.e("Relatorio", "Datas inválidas")
            return
        }

        viewModelScope.launch {
            try {
                Log.d("FIREBASE", "Buscando dados do período $inicio até $fim")

                val (consumo, desperdicios, naoAtendidos) =
                    repo.buscarPeriodo(inicio, fim)

                Log.d("FIREBASE", "Consumo size = ${consumo.size}")
                Log.d("FIREBASE", "Desperdicios size = ${desperdicios.size}")
                Log.d("FIREBASE", "NaoAtendidos size = ${naoAtendidos.size}")

                val pdfGenerator = PdfGenerator(context)

                val file = pdfGenerator.gerarRelatorio(
                    consumo = consumo,
                    desperdicios = desperdicios,
                    naoAtendidos = naoAtendidos
                )

                Log.d("PDF", "PDF GERADO COM DADOS: ${file.absolutePath}")

            } catch (e: Exception) {
                Log.e("PDF", "Erro ao gerar relatório", e)
            }
        }
    }
}