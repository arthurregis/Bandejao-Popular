package com.example.restaurantepopular.ui.componets.registerbalance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantepopular.ui.componets.datepickercardapio.DatePickerCardapio

@Composable
fun FoodFormSection(
    title: String,
    fields: List<String>,
    showKg: Boolean = true,
    clearFormTrigger: Int,
    onRegister: (data: String, dados: Map<String, String>) -> Unit
) {
    var dataSelecionada by remember { mutableStateOf("") }

    val inputValues = remember {
        mutableStateMapOf<String, String>().apply {
            fields.forEach { put(it, "") }
        }
    }

    // 🔥 LIMPA O FORMULÁRIO QUANDO O TRIGGER MUDA
    LaunchedEffect(clearFormTrigger) {
        dataSelecionada = ""
        fields.forEach { inputValues[it] = "" }
    }

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray)

        DatePickerCardapio(
            selectedDate = dataSelecionada,
            onDateSelected = { dataSelecionada = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        fields.forEach { label ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(label, fontWeight = FontWeight.Bold, fontSize = 14.sp)

                OutlinedTextField(
                    value = inputValues[label] ?: "",
                    onValueChange = { inputValues[label] = it },
                    modifier = Modifier.width(130.dp).height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = {
                        if (showKg) Text("KG", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Button(
            onClick = { onRegister(dataSelecionada, inputValues.toMap()) },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A57C8)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("CADASTRAR", fontWeight = FontWeight.Bold)
        }
    }
}