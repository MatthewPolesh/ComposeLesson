package com.example.composelesson

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.composelesson.AccountScreen.Card
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings


interface UserRepository {
    suspend fun registerUser(email: String, password: String, name: String, phone: String): Result<String?>
    suspend fun signInUser(email: String, password: String): Result<Unit>
    suspend fun addUserToFirestore(uid: String, userInfo: Map<String, Any>): Result<Unit>
    suspend fun singOut()
    suspend fun sendPasswordResetEmail(email: String, context: Context, callback: (Boolean, String?) -> Unit)
    suspend fun addBankCard(userId: String?, card: Card, callback: (Boolean) -> Unit)
    suspend fun deleteBankCard(userId: String?, cardId: String, callback: (Boolean) -> Unit)
    suspend fun getBankCards(userId: String?, callback: (List<Pair<String, Card?>>) -> Unit)
}


class FireBaseAccount : UserRepository {
    private val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    val auth = _auth
    private val _firestore = Firebase.firestore.apply {
        firestoreSettings = firestoreSettings{ isPersistenceEnabled = true }
    }
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

    override suspend fun sendPasswordResetEmail(email: String, context: Context, callback: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    override suspend fun addBankCard(userId: String?, card: Card, callback: (Boolean) -> Unit) {
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("cards")
                .add(card)
                .addOnSuccessListener { documentReference ->
                    // Bank card successfully added
                    callback(true)
                }
                .addOnFailureListener { e ->
                    // Error adding bank card
                    callback(false)
                }
        }
    }

    override suspend fun deleteBankCard(userId: String?, cardId: String, callback: (Boolean) -> Unit) {
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("cards")
                .document(cardId)
                .delete()
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener { e ->
                    callback(false)
                }
        }
    }

    override suspend fun getBankCards(
        userId: String?,
        callback: (List<Pair<String, Card?>>) -> Unit
    ) {
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("cards")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val cards = querySnapshot.documents.map { document ->
                        val card = document.toObject(Card::class.java)
                        Pair(document.id, card)
                    }
                    callback(cards)
                }
                .addOnFailureListener { e ->
                    callback(emptyList())
                }
        }
    }

    override suspend fun singOut() {
        auth.signOut()
    }
}


