package com.example.jetpack_compose_myfirstfirebaseapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_myfirstfirebaseapp.navigation.AppRoutes
import com.example.jetpack_compose_myfirstfirebaseapp.ui.HomeScreen
import com.example.jetpack_compose_myfirstfirebaseapp.ui.LoginScreen
import com.example.jetpack_compose_myfirstfirebaseapp.ui.RegisterScreen
import com.example.jetpack_compose_myfirstfirebaseapp.ui.theme.Jetpack_compose_MyFirstFirebaseAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_compose_MyFirstFirebaseAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                   //RegisterScreen()

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = AppRoutes.LOGIN){
                        composable(AppRoutes.REGISTER){
                            RegisterScreen {
                                navController.navigate(AppRoutes.HOME) {
                                    popUpTo(AppRoutes.LOGIN) { inclusive = true }
                                }
                            }
                        }

                        composable(AppRoutes.LOGIN){
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(AppRoutes.HOME) {
                                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                                    }

                                }

                            ){
                                navController.navigate(AppRoutes.REGISTER)
                            }
                        }

                        composable(AppRoutes.HOME){
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}
