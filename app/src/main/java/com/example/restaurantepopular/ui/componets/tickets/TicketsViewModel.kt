package com.example.restaurantepopular.ui.componets.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantepopular.datasource.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TicketsViewModel : ViewModel() {

    private val dataSource = DataSource()

    private val _tickets = MutableStateFlow(0)
    val tickets = _tickets

    init {
        loadTickets()
    }

    private fun loadTickets() {
        viewModelScope.launch {
            _tickets.value = dataSource.getTickets()
        }
    }

    fun updateTickets(newValue: Int) {
        viewModelScope.launch {
            dataSource.updateTickets(newValue)
            // atualiza na tela imediatamente
            _tickets.value = newValue
        }
    }
}