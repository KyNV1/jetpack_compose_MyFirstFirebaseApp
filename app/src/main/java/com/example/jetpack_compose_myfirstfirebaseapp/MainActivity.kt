package com.example.jetpack_compose_myfirstfirebaseapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
                   RegisterScreen()
                }
            }
        }
    }
}
