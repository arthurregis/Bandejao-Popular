package com.example.restaurantepopular.ui.componets.registerMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantepopular.data.CardapioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterMenuViewModel : ViewModel() {

    private val repository = CardapioRepository()

    // Estado da operação (salvando / sucesso / erro)
    private val _status = MutableStateFlow("")
    val status: StateFlow<String> = _status

    //Salvar cardápio no Firestore
    fun salvarCardapio(
        data: String,
        proteina: String,
        acompanhamento: String,
        guarnicao: String,
        salada: String,
        sobremesa: String
    ) {
        viewModelScope.launch {
            _status.value = "salvando"

            val sucesso = repository.salvarCardapio(
                data,
                proteina,
                acompanhamento,
                guarnicao,
                salada,
                sobremesa
            )

            _status.value = if (sucesso) "sucesso" else "erro"
        }
    }

    fun resetarStatus() {
        _status.value = ""
    }

}