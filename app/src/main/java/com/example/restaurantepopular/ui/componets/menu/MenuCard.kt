package com.example.restaurantepopular.ui.componets.menu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantepopular.model.Cardapio
import com.example.restaurantepopular.viewmodel.CardapioViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MenuCard(
    data: String,
    cardapio: Cardapio?,
    onDiaAnterior: () -> Unit,
    onProximoDia: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFFF2F2F2))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "<",
                    modifier = Modifier.clickable { onDiaAnterior() },
                    fontSize = 30.sp,
                )

                Text(
                    text = data,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )

                Text(
                    text = ">",
                    modifier = Modifier.clickable { onProximoDia() },
                    fontSize = 30.sp,
                )
            }

            Spacer(Modifier.height(12.dp))

            DividerAzul()

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SecaoTitulo("Proteína")
                TextoItem(cardapio?.proteina)

                DividerAzul()

                SecaoTitulo("Acompanhamento")
                TextoItem(cardapio?.acompanhamento)

                DividerAzul()

                SecaoTitulo("Guarnição")
                TextoItem(cardapio?.guarnicao)

                DividerAzul()

                SecaoTitulo("Salada")
                TextoItem(cardapio?.salada)

                DividerAzul()

                SecaoTitulo("Sobremesa")
                TextoItem(cardapio?.sobremesa)
            }
        }
    }
}