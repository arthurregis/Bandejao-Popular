package com.example.restaurantepopular.ui.screens.registeruser

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restaurantepopular.data.repository.AuthRepository
import com.example.restaurantepopular.ui.componets.successpopup.SuccessPopup
import com.example.restaurantepopular.ui.navigation.Routes

@Composable
fun RegisterUserScreen(
    navController: NavController,
    onGoogleClick: () -> Unit = {},
    onRegisterClick: (String, String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var error by remember { mutableStateOf<String?>(null) }

    var showSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Acesso ao Sistema",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 128.dp)
        )

        // Nome
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            label = { Text("Seu nome completo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Senha
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Senha") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = {
//                val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
//                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(icon, contentDescription = "Mostrar/ocultar senha")
//                }
//            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Confirmar Senha
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            singleLine = true,
            label = { Text("Confirmar Senha") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = {
//                val icon = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
//                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
//                    Icon(icon, contentDescription = "Mostrar/ocultar confirmação")
//                }
//            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp)
        )

        error?.let {
            Text(it, color = Color.Red)
        }

        // Botão CADASTRAR
        Button(
            onClick = {
                onRegisterClick(name, email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0052CC),
                contentColor = Color.White
            )
        ) {
            Text("CADASTRAR")
        }

        if (showSuccess) {
            SuccessPopup(
                message = "Usuário cadastrado com sucesso!",
//                visible = showSuccess,
                onDismiss = {
                    showSuccess = false
                    //Ir para Login ou Home
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                }
            )
        }

        Spacer(Modifier.height(32.dp))

        // Entrar com Google
        OutlinedButton(
            onClick = onGoogleClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(30.dp),
            border = BorderStroke(1.dp, Color(0xFFEAEAEA))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Entrar com Google")
                Spacer(Modifier.width(8.dp))
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_google),
//                    contentDescription = "Google",
//                    modifier = Modifier.size(20.dp)
//                )
            }
        }
    }
}