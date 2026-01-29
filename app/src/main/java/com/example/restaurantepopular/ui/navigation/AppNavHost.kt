package com.example.restaurantepopular.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantepopular.data.repository.AuthRepository
import com.example.restaurantepopular.domain.session.SessionManager
import com.example.restaurantepopular.domain.session.UserRole
import com.example.restaurantepopular.ui.screens.adminhome.AdminHome
import com.example.restaurantepopular.ui.screens.attendanthome.AtendenteScreen
import com.example.restaurantepopular.ui.screens.feedback.FeedBackScreen
import com.example.restaurantepopular.ui.screens.login.LoginScreen
import com.example.restaurantepopular.ui.screens.login.LoginViewModel
import com.example.restaurantepopular.ui.screens.management.ManagementScreen
import com.example.restaurantepopular.ui.screens.profile.ProfileScreen
import com.example.restaurantepopular.ui.screens.registeruser.RegisterUserScreen
import com.example.restaurantepopular.ui.screens.registeruser.RegisterUserViewModel
import com.example.restaurantepopular.ui.screens.registrations.RegistrationsScreen
import com.example.restaurantepopular.ui.screens.reports.ReportsRoute
import com.example.restaurantepopular.ui.screens.userhome.UserScreen

fun navigateToHome(navController: NavHostController) {
    navController.navigate(Routes.HOME_RESOLVER) {
        popUpTo(Routes.LOGIN) { inclusive = true }
        launchSingleTop = true
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.HOME_RESOLVER) {
            val email = AuthRepository.getUserEmail()

            LaunchedEffect(email) {
                val route = when {
                    email == "admin@gmail.com" -> Routes.HOME_ADMIN
                    email?.contains("atendente", true) == true -> Routes.HOME_ATENDENTE
                    else -> Routes.HOME_USER
                }

                navController.navigate(route) {
                    popUpTo(Routes.HOME_RESOLVER) { inclusive = true }
                }
            }
        }

        composable(Routes.HOME_RESOLVER) {

            LaunchedEffect(Unit) {

                val route = when (SessionManager.userRole) {
                    UserRole.ADMIN -> Routes.HOME_ADMIN
                    UserRole.ATENDENTE -> Routes.HOME_ATENDENTE
                    UserRole.USER -> Routes.HOME_USER
                    null -> Routes.LOGIN
                }

                navController.navigate(route) {
                    popUpTo(Routes.HOME_RESOLVER) { inclusive = true }
                }
            }
        }

        /* ---------------- LOGIN ---------------- */

        composable(Routes.LOGIN) {

            val viewModel: LoginViewModel = viewModel()

            LoginScreen(
                onLoginClick = { email, password ->
                    viewModel.login(
                        email = email,
                        password = password,
                        onSuccess = { route ->
                            navController.navigate(route) {
                                popUpTo(Routes.LOGIN) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        },
                        onError = {
                            // tratar erro se quiser
                        }
                    )
                },
                onRegisterClick = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        /* ---------------- REGISTER ---------------- */

        composable(Routes.REGISTER) {

            val viewModel: RegisterUserViewModel = viewModel()

            RegisterUserScreen(
                navController = navController,
                onRegisterClick = { name, email, password, onSuccess, onError ->
                    viewModel.registerUser(
                        name = name,
                        email = email,
                        password = password,
                        onSuccess = {
                            onSuccess()
                        },
                        onError = { msg ->
                            onError(msg)
                        }
                    )
                }
            )
        }

        /* ---------------- HOME USER ---------------- */

        composable(Routes.HOME_USER) {
            UserScreen(
                onNavAvaliar = {
                    navController.navigate(Routes.AVALIAR) {
                        launchSingleTop = true
                    }
                },
                onNavProfile = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        /* ---------------- HOME ADMIN ---------------- */
        composable(Routes.HOME_ADMIN) {
            AdminHome(
                onCadastros = {
                    navController.navigate(Routes.GERENCIAMENTO) {
                        launchSingleTop = true
                    }
                },
                onNavProfile = {
                    navController.navigate(Routes.PROFILE)
                },
                onNavHome = {
                    navigateToHome(navController)
                }
            )
        }

        /* ---------------- HOME ATENDENTE ---------------- */

        composable(Routes.HOME_ATENDENTE) {
            AtendenteScreen(
                onNavAvaliar = {
                    navController.navigate(Routes.AVALIAR) {
                        launchSingleTop = true
                    }
                },
                onNavProfile = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        /* ---------------- FEEDBACK ---------------- */

        composable(Routes.AVALIAR) {
            FeedBackScreen(
                onFeedbackSuccess = {
                },
                onNavHome = {
                    navigateToHome(navController)
                },
                onNavAvaliar = {},
                onNavProfile = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        /* ---------------- PROFILE ---------------- */

        composable(Routes.PROFILE) {
            ProfileScreen(
                onLogoutClick = {
                    AuthRepository.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0)
                    }
                },
                onNavHome = {
                    navigateToHome(navController)
                },
                onNavAvaliar = {
                    navController.navigate(Routes.AVALIAR)
                },
                onNavProfile = {}
            )
        }

        composable(Routes.GERENCIAMENTO) {
            ManagementScreen(
                onGerenciar = { /* ... */ },
                onNavProfile = { navController.navigate(Routes.PROFILE) },
                onNavHome = { navigateToHome(navController) },
                onConsumo = { navController.navigate(Routes.CONSUMO) },
                onCadastros = {
                    navController.navigate(Routes.CADASTROS)
                }
            )
        }

        composable(Routes.CADASTROS) {
            RegistrationsScreen(
                onGerenciar = { navController.navigate(Routes.GERENCIAMENTO) },
                onNavProfile = { navController.navigate(Routes.PROFILE) },
                onNavHome = { navigateToHome(navController)}

            )
        }

        composable(Routes.CONSUMO) {
            ReportsRoute()
        }
    }
}