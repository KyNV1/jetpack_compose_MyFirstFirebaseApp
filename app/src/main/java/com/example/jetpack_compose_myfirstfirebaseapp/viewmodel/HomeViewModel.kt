package com.example.jetpack_compose_myfirstfirebaseapp.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_myfirstfirebaseapp.model.User
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel: ViewModel(){
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val users = mutableStateListOf<User>()

    @SuppressLint("RestrictedApi")
    fun fetchUsers(){
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                users.clear()
                for(document in result){
                    val user = User(
                        id = document.id,
                        fullName = document.getString("fullName") ?: "No Name",
                    )
                    users.add(user)
                }
            }
            .addOnFailureListener { exception ->
                // TODO Handle failure

            }
    }



}