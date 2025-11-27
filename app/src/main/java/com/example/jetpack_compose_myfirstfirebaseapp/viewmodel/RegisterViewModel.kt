package com.example.jetpack_compose_myfirstfirebaseapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var isLoading by mutableStateOf(false)
        private set

    var statusMessage by mutableStateOf("")
        private set

    fun registerAndSaveData(email: String, pass: String, fullName: String) {
        if (email.isBlank() || pass.isBlank() || fullName.isBlank()) {
            statusMessage = "Please enter all required information"
            return
        }

        isLoading = true
        statusMessage = "Processing..."

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                        Log.d("AuthDemo", "Authentication successful, starting to save to Firestore..")
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        saveToFirestore(userId, fullName)
                    }else{
                        isLoading = false
                        statusMessage = "Error: Unable to retrieve user ID"
                    }
                } else {
                    isLoading = false
                    statusMessage = "error  register: ${task.exception?.message}"
                    Log.d("AuthDemo", "error register: ${task.exception?.message}")
                }
            }
    }

    private fun saveToFirestore(userId: String, fullName: String) {
        val userMap = hashMapOf(
            "fullName" to fullName,
            "createdAt" to System.currentTimeMillis()
        )

        db.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener {
                isLoading = false
                statusMessage = "Success! Welcome $fullName"
            }
            .addOnFailureListener { e ->
                isLoading = false
                statusMessage = "Error saving data:: ${e.message}"
            }
    }
}