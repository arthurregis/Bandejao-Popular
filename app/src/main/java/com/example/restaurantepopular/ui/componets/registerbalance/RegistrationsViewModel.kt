import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantepopular.data.ConsumoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationsViewModel : ViewModel() {

    private val repository = ConsumoRepository()

    // Status individual para cada operação (opcional, ou use um geral como no seu exemplo)
    private val _status = MutableStateFlow("")
    val status: StateFlow<String> = _status

    fun salvarConsumo(data: String, dados: Map<String, String>) {
        executarSalvamento {
            repository.salvarConsumo(
                data,
                dados["PROTEÍNA:"] ?: "",
                dados["ACOMPANHAMENTO:"] ?: "",
                dados["GUARNIÇÕES:"] ?: "",
                dados["SALADA:"] ?: ""
            )
        }
    }

    fun salvarDesperdicio(data: String, dados: Map<String, String>) {
        executarSalvamento {
            repository.salvarDesperdicio(
                data,
                dados["PROTEÍNA:"] ?: "",
                dados["ACOMPANHAMENTO:"] ?: "",
                dados["GUARNIÇÕES:"] ?: "",
                dados["SALADA:"] ?: ""
            )
        }
    }

    fun salvarNaoAtendidos(data: String, quantidade: String) {
        executarSalvamento {
            repository.salvarNaoAtendidos(data, quantidade)
        }
    }

    private fun executarSalvamento(block: suspend () -> Boolean) {
        viewModelScope.launch {
            _status.value = "salvando"
            val sucesso = block()
            _status.value = if (sucesso) "sucesso" else "erro"
        }
    }

    fun resetarStatus() {
        _status.value = ""
    }
}