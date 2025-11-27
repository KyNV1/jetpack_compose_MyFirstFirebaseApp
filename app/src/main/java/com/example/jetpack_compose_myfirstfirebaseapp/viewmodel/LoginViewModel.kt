package com.example.jetpack_compose_myfirstfirebaseapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var isLoading by mutableStateOf(false)
        private set

    var statusMessage by mutableStateOf("")
        private set

    var isLoginSuccess by mutableStateOf(false)
        private set

    fun login(email: String, pass: String){
        if(email.isBlank() || pass.isBlank()){
            statusMessage = "Please enter all required information"
            return
        }

        isLoading = true
        statusMessage = "Processing..."

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                isLoading = false
                if (task.isSuccessful) {
                    statusMessage = "Success! Welcome back"
                    isLoginSuccess = true
                } else {
                    statusMessage = "Error: ${task.exception?.message}"
                }
            }


    }

}