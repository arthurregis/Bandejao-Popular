package com.example.restaurantepopular.ui.componets.datepickercardapio

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerCardapio(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val openDialog = remember { mutableStateOf(false) }

    // Botão que abre o calendário
    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("DATA") },
        trailingIcon = {
            IconButton(onClick = { openDialog.value = true }) {
                Icon(Icons.Default.DateRange, contentDescription = "Selecionar data")
            }
        }
    )

    // Diálogo do Calendar
    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false

                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = SimpleDateFormat(
                                 "yyyy-MM-dd",
                                Locale.getDefault()
                            ).format(Date(millis))

                            onDateSelected(date)
                        }
                    }
                ) {
                    Text("OK")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
