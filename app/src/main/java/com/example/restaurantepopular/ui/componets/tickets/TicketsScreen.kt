package com.example.restaurantepopular.ui.componets.tickets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TicketsScreen(viewModel: TicketsViewModel = viewModel()) {

    val tickets by viewModel.tickets.collectAsState()

    var editableTickets by remember { mutableStateOf(tickets.toString()) }

    var isEditing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FICHAS RESTANTES:",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                color = Color(0xFF0A57C8),
                thickness = 2.dp,
                modifier = Modifier.weight(1f)
            )

            if (isEditing) {
                // Campo editável invisível (estilizado como número grande)
                BasicTextField(
                    value = editableTickets,
                    onValueChange = { value ->
                        // Permite apagar tudo
                        if (value.isEmpty()) {
                            editableTickets = ""
                            return@BasicTextField
                        }

                        // Permite apenas números
                        if (value.all { it.isDigit() }) {
                            val intValue = value.toIntOrNull() ?: 0
                            editableTickets = intValue.coerceIn(0, 300).toString()
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.width(80.dp)
                )
            } else {
                // Texto normal, ao clicar vira editável
                Text(
                    text = tickets.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .width(80.dp)
                        .clickable {
                        isEditing = true
                        editableTickets = tickets.toString()
                    },
                    textAlign = TextAlign.Center
                )
            }

            HorizontalDivider(
                color = Color(0xFF0A57C8),
                thickness = 2.dp,
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(0.9f),
            onClick = {
                val newValue = editableTickets.toIntOrNull()?.coerceIn(0, 300) ?: 0
                viewModel.updateTickets(newValue)
                isEditing = false
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0A57C8)
            )
        ) {
            Text(
                text = "ATUALIZAR",
                fontSize = 20.sp,
            )
        }
    }
}
