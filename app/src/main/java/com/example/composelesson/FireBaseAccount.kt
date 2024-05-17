package com.example.composelesson

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

//Работа с данными

interface UserRepository {
    suspend fun registerUser(email: String, password: String, name: String, phone: String): Result<String?>
    suspend fun signInUser(email: String, password: String): Result<Unit>
    suspend fun addUserToFirestore(uid: String, userInfo: Map<String, Any>): Result<Unit>
    suspend fun singOut()
}


class FireBaseAccount : UserRepository {
    private val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    val auth = _auth
    private val _firestore = Firebase.firestore
    val firestore = _firestore

    override suspend fun registerUser(email: String, password: String, name: String, phone: String): Result<String?> =
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

    override suspend fun signInUser(email: String, password: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            if (email.isEmpty() || password.isEmpty()) {
                return@withContext Result.failure(IllegalArgumentException("Все поля должны быть заполнены"))
            } else {
                auth.signInWithEmailAndPassword(email, password).await()
                return@withContext Result.success(Unit)
            }
        }

    override suspend fun addUserToFirestore(uid: String, userInfo: Map<String, Any>): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                firestore.collection("users").document(uid).set(userInfo).await()
                return@withContext Result.success(Unit)
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }

    override suspend fun singOut() {
        auth.signOut()
    }
}


