package com.example.restaurantepopular.ui.screens.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantepopular.ui.componets.datepickercardapio.DatePickerCardapio

@Composable
fun ReportsRoute(
    viewModel: ReportsViewModel = viewModel()
) {
    val context = LocalContext.current

    ReportsScreen(
        onGeneratePdf = { inicio, fim, tipos ->
            viewModel.gerarPdf(context, inicio, fim, tipos)
        }
    )
}
@Composable
fun ReportsScreen(
    onGeneratePdf: (start: String, end: String, tipos: List<String>) -> Unit,
) {
    var dataInicio by remember { mutableStateOf("") }
    var dataFim by remember { mutableStateOf("") }

    var consumo by remember { mutableStateOf(true) }
    var desperdicio by remember { mutableStateOf(true) }
    var naoAtendidos by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("Relatórios", style = MaterialTheme.typography.headlineMedium)

        DatePickerCardapio(
            selectedDate = dataInicio,
            onDateSelected = { dataInicio = it }
        )

        DatePickerCardapio(
            selectedDate = dataFim,
            onDateSelected = { dataFim = it }
        )

        Text("Incluir no relatório:")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(consumo, { consumo = it })
            Text("Consumo")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(desperdicio, { desperdicio = it })
            Text("Desperdícios")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(naoAtendidos, { naoAtendidos = it })
            Text("Não Atendidos")
        }

        val context = LocalContext.current

        Button(
            enabled = dataInicio.isNotBlank() && dataFim.isNotBlank(),
            onClick = {
                val tipos = mutableListOf<String>()
                if (consumo) tipos.add("consumo")
                if (desperdicio) tipos.add("desperdicios")
                if (naoAtendidos) tipos.add("nao_atendidos")

                onGeneratePdf(dataInicio, dataFim, tipos)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GERAR PDF")
        }
    }
}
