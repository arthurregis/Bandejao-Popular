package com.example.restaurantepopular.ui.screens.cadastroconsumos

import RegistrationsViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantepopular.ui.componets.registerbalance.FoodFormSection
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.restaurantepopular.ui.componets.successpopup.SuccessPopup

@Composable
fun CadastroConsumos(viewModel: RegistrationsViewModel = viewModel()) {
    val status by viewModel.status.collectAsState()
    var showPopup by remember { mutableStateOf(false) }
    var clearTrigger by remember { mutableIntStateOf(0) }

    LaunchedEffect(status) {
        if (status == "sucesso") {
            showPopup = true
            delay(1500)
            clearTrigger++
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()      // ⬅️ NÃO fillMaxSize
            .padding(20.dp),     // ⬅️ SEM verticalScroll
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FoodFormSection(
            title = "CADASTRAR CONSUMO:",
            fields = listOf("PROTEÍNA:", "ACOMPANHAMENTO:", "GUARNIÇÕES:", "SALADA:"),
            clearFormTrigger = clearTrigger,
            onRegister = { data, dados -> viewModel.salvarConsumo(data, dados) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        FoodFormSection(
            title = "CADASTRAR DESPERDÍCIOS:",
            fields = listOf("PROTEÍNA:", "ACOMPANHAMENTO:", "GUARNIÇÕES:", "SALADA:"),
            clearFormTrigger = clearTrigger,
            onRegister = { data, dados -> viewModel.salvarDesperdicio(data, dados) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        FoodFormSection(
            title = "CADASTRAR NÃO ATENDIDOS:",
            fields = listOf("NÃO ATENDIDOS:"),
            showKg = false,
            clearFormTrigger = clearTrigger,
            onRegister = { data, dados ->
                viewModel.salvarNaoAtendidos(data, dados["NÃO ATENDIDOS:"] ?: "")
            }
        )

        AnimatedVisibility(visible = status.isNotEmpty()) {
            Text(
                text = when (status) {
                    "salvando" -> "Salvando..."
                    "sucesso" -> "Dados salvos com sucesso!"
                    "erro" -> "Erro ao salvar no Firebase!"
                    else -> ""
                },
                color = if (status == "erro") Color.Red else Color(0xFF008000),
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }

    if (showPopup) {
        SuccessPopup(
            message = "Cadastro realizado com sucesso!",
            onDismiss = { showPopup = false }
        )
    }
}