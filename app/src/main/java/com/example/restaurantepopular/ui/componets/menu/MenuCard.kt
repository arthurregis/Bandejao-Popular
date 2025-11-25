package com.example.restaurantepopular.ui.componets.menu

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuScreen() {
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
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = "<",
                    fontSize = 30.sp,
                )
                Text(
                    text = "TERÇA - 10/11/2025",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(
                    text = ">",
                    fontSize = 30.sp,
                )
            }

            Spacer(Modifier.height(12.dp))

            HorizontalDivider(
                color = Color(0xFF0A57C8),
                thickness = 2.dp,
                modifier = Modifier
                    .weight(0.1f)
            )

            Column ( modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Proteína:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "SUÍNO ACEBOLADO",
                    fontSize = 22.sp,
                )

                Text(
                    text = "FRANGO GRELHADO",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                HorizontalDivider(
                    color = Color(0xFF0A57C8),
                    thickness = 2.dp,
                    modifier = Modifier
                        .weight(0.1f)
                )

                Text(
                    text = "Acompanhamento:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "FAROFA",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                HorizontalDivider(
                    color = Color(0xFF0A57C8),
                    thickness = 2.dp,
                    modifier = Modifier.weight(0.1f)
                )

                Text(
                    text = "Guarnições:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "ARROZ BRANCO",
                    fontSize = 22.sp,
                )

                Text(
                    text = "FEIJÃO CARIOCA",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                HorizontalDivider(
                    color = Color(0xFF0A57C8),
                    thickness = 2.dp,
                    modifier = Modifier.weight(0.1f)
                )

                Text(
                    text = "Salada:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "REPOLHO BRANCO",
                    fontSize = 22.sp,
                )

                Text(
                    text = "BETERRABA",
                    fontSize = 22.sp,
                )

                Text(
                    text = "CENOURA",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                HorizontalDivider(
                    color = Color(0xFF0A57C8),
                    thickness = 2.dp,
                    modifier = Modifier.weight(0.1f)
                )

                Text(
                    text = "Sobremesa:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "DOCE",
                    fontSize = 22.sp,
                )
            }

        }
    }
}
