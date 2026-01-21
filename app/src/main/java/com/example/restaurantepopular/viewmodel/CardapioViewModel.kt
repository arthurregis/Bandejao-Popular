package com.example.restaurantepopular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantepopular.data.CardapioRepository
import com.example.restaurantepopular.model.Cardapio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class CardapioViewModel : ViewModel() {

    private val repository = CardapioRepository()

    private val _cardapio = MutableStateFlow<Cardapio?>(null)
    val cardapio: StateFlow<Cardapio?> = _cardapio

    private val _dataSelecionada = MutableStateFlow(LocalDate.now())
    val dataSelecionada: StateFlow<LocalDate> = _dataSelecionada

    init {
        carregarCardapioAtual()
    }

    private fun carregarCardapioAtual() {
        viewModelScope.launch {
            _cardapio.value =
                repository.buscarCardapio(_dataSelecionada.value.toString())
        }
    }

    fun diaAnterior() {
        atualizarData(_dataSelecionada.value.minusDays(1))
    }

    fun proximoDia() {
        atualizarData(_dataSelecionada.value.plusDays(1))
    }

    fun selecionarData(data: LocalDate) {
        atualizarData(data)
    }

    private fun atualizarData(novaData: LocalDate) {
        _dataSelecionada.value = novaData

        viewModelScope.launch {
            _cardapio.value =
                repository.buscarCardapio(novaData.toString())
        }
    }
}