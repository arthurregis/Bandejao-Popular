package com.example.restaurantepopular.ui.screens.feedback

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantepopular.data.repository.FeedbackRepository
import com.example.restaurantepopular.ui.componets.navBar.NavBar
import com.example.restaurantepopular.ui.componets.banner.Banner
import com.example.restaurantepopular.ui.componets.feedback.FeedBackItem
import com.example.restaurantepopular.ui.componets.successpopup.SuccessPopup
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FeedBackScreen(
    onFeedbackSuccess: () -> Unit,
    onNavHome: () -> Unit,
    onNavAvaliar: () -> Unit,
    onNavProfile: () -> Unit,
) {
    var avaliacao by remember { mutableStateOf<Avaliacao?>(null) }
    var sugestao by remember { mutableStateOf("") }

    var showSuccessPopup by remember { mutableStateOf(false) }

    val viewModel: FeedbackViewModel = viewModel()

    Scaffold(
        topBar = { Banner() },
        bottomBar = {
            NavBar(
                onHome = onNavHome,
                onAvaliar = onNavAvaliar,
                onProfile = onNavProfile
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                "AVALIAR REFEIÇÃO",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            val dataAtual = remember {
                LocalDate.now().format(
                    DateTimeFormatter.ofPattern(
                        "dd / MMM / yyyy",
                        Locale.forLanguageTag("pt-BR")
                    )
                ).uppercase()
            }

            OutlinedTextField(
                value = dataAtual,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FeedBackItem(
                    text = "RUIM",
                    color = Color.Red,
                    icon = { Icon(Icons.Default.SentimentVeryDissatisfied, null) },
                    selected = avaliacao == Avaliacao.RUIM
                ) { avaliacao = Avaliacao.RUIM }

                FeedBackItem(
                    text = "REGULAR",
                    color = Color(0xFFFFA000),
                    icon = { Icon(Icons.Default.SentimentNeutral, null) },
                    selected = avaliacao == Avaliacao.REGULAR
                ) { avaliacao = Avaliacao.REGULAR }

                FeedBackItem(
                    text = "BOM",
                    color = Color(0xFF2E7D32),
                    icon = { Icon(Icons.Default.SentimentSatisfied, null) },
                    selected = avaliacao == Avaliacao.BOM
                ) { avaliacao = Avaliacao.BOM }

                FeedBackItem(
                    text = "ÓTIMO",
                    color = Color(0xFF00C853),
                    icon = { Icon(Icons.Default.SentimentVerySatisfied, null) },
                    selected = avaliacao == Avaliacao.OTIMO
                ) { avaliacao = Avaliacao.OTIMO }
            }

            // Sugestão
            OutlinedTextField(
                value = sugestao,
                onValueChange = { sugestao = it },
                label = { Text("Sugestão (Opcional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Empurra o botão para o final
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (avaliacao != null) {
                        FeedbackRepository.sendFeedback(
                            satisfaction = avaliacao!!.name,
                            suggestion = sugestao.trim(),
                            onSuccess = {
                                showSuccessPopup = true
                            },
                            onError = { error ->
                                println(error)
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A57C8),
                    contentColor = Color.White
                )
            ) {
                Text("AVALIAR", fontSize = 20.sp)
            }

            if (showSuccessPopup) {
                SuccessPopup(
                    message = "Avaliação enviada com sucesso!",
                    onDismiss = {
                        showSuccessPopup = false
                        onFeedbackSuccess()
                    }
                )
            }
        }
    }
}
enum class Avaliacao(val label: String) {
    RUIM("Ruim"),
    REGULAR("Regular"),
    BOM("Bom"),
    OTIMO("Ótimo")
}