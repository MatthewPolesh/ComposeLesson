package com.example.composelesson

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
//Работа с данными
class FireBaseAccount {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: String, password: String, name: String, phone: String
    ): Result<String?> =
        withContext(Dispatchers.IO) {
            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                return@withContext Result.failure(IllegalArgumentException("Все поля должны быть заполнены"))
            } else if (password.length < 6) {
                return@withContext Result.failure(IllegalArgumentException("Пароль должен содержать более 6 символов"))
            } else {
                val res = auth.createUserWithEmailAndPassword(email, password).await()
                return@withContext Result.success(res.user?.uid)
            }
        }
    suspend fun singInUser(email: String, password: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            if (email.isEmpty() || password.isEmpty()) {
                return@withContext Result.failure(IllegalArgumentException("Все поля должны быть заполнены"))
            } else {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {}.await()
                return@withContext Result.success(Unit)
            }
        }
}

