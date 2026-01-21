package com.example.restaurantepopular.ui.screens.registerMenu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantepopular.ui.componets.datepickercardapio.DatePickerCardapio
import kotlinx.coroutines.delay
import androidx.compose.animation.AnimatedVisibility
import com.example.restaurantepopular.ui.componets.successpopup.SuccessPopup


@Composable
fun RegisterMenuScreen(
    viewModel: RegisterMenuViewModel = viewModel()
) {
    // Campos editáveis
    var dataSelecionada by remember { mutableStateOf("") }
    var proteina by remember { mutableStateOf("") }
    var acompanhamento by remember { mutableStateOf("") }
    var guarnicao by remember { mutableStateOf("") }
    var salada by remember { mutableStateOf("") }
    var sobremesa by remember { mutableStateOf("") }

    // popUp
    var showPopup by remember { mutableStateOf(false) }

    // Status da operação (salvando / sucesso / erro)
    val status by viewModel.status.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CADASTRAR CARDÁPIO:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 8.dp)
            )

            DatePickerCardapio(
                selectedDate = dataSelecionada,
                onDateSelected = { dataSelecionada = it }
            )


            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --------------------------------------------------
        // PROTEÍNA
        // --------------------------------------------------
        Text("PROTEÍNA:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = proteina,
            onValueChange = { proteina = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = false,              // permite múltiplas linhas
            maxLines = 3     // permite quebrar para baixo
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --------------------------------------------------
        // ACOMPANHAMENTO
        // --------------------------------------------------
        Text("ACOMPANHAMENTO:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = acompanhamento,
            onValueChange = { acompanhamento = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --------------------------------------------------
        // GUARNIÇÃO
        // --------------------------------------------------
        Text("GUARNIÇÃO:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = guarnicao,
            onValueChange = { guarnicao = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = false,              // permite múltiplas linhas
            maxLines = 3         // permite quebrar para baixo
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --------------------------------------------------
        // SALADA
        // --------------------------------------------------
        Text("SALADA:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = salada,
            onValueChange = { salada = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = false,              // permite múltiplas linhas
            maxLines = 3        // permite quebrar para baixo
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --------------------------------------------------
        // SOBREMESA
        // --------------------------------------------------
        Text("SOBREMESA:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = sobremesa,
            onValueChange = { sobremesa = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )

        LaunchedEffect(status) {
            if (status == "sucesso") {

                // mostra o popup
                showPopup = true

                // limpa os campos
                dataSelecionada = ""
                proteina = ""
                acompanhamento = ""
                guarnicao = ""
                salada = ""
                sobremesa = ""

                delay(1500) // o popup já fecha sozinho mas isso mantém o fluxo

                viewModel.resetarStatus()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --------------------------------------------------
        // BOTÃO CADASTRAR
        // --------------------------------------------------
        Button(
            onClick = {
                viewModel.salvarCardapio(
                    dataSelecionada,
                    proteina,
                    acompanhamento,
                    guarnicao,
                    salada,
                    sobremesa
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0A57C8),
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("CADASTRAR", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // --------------------------------------------------
        // STATUS
        // --------------------------------------------------
        AnimatedVisibility(visible = status.isNotEmpty()) {
            Text(
                text = when (status) {
                    "salvando" -> "Salvando..."
                    "sucesso" -> "Cardápio salvo com sucesso!"
                    "erro" -> "Erro ao salvar no Firebase!"
                    else -> ""
                },
                color = when (status) {
                    "salvando" -> Color.Gray
                    "sucesso" -> Color(0xFF008000)
                    "erro" -> Color.Red
                    else -> Color.Transparent
                },
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
    if (showPopup) {
        SuccessPopup(
            message = "Cardápio salvo com sucesso!",
            onDismiss = { showPopup = false }
        )
    }
}