package com.example.composelesson

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.composelesson.AccountScreen.Card
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FireBaseAccountTest {

    private val firebaseAccount = FireBaseAccount()

    @Test
    fun testRegisterUser() = runBlocking {
        val email = "test2@example.com"
        val password = "password123"
        val name = "Test User"
        val phone = "1234567890"

        val result = firebaseAccount.registerUser(email, password, name, phone)
        assertTrue(result.isSuccess)
    }

    @Test
    fun testSignInUser() = runBlocking {
        val email = "test@example.com"
        val password = "password123"

        val result = firebaseAccount.signInUser(email, password)
        assertTrue(result.isSuccess)
    }

    @Test
    fun testAddUserToFirestore() = runBlocking {
        val uid = "testUID"
        val userInfo = mapOf(
            "name" to "Test User",
            "email" to "test@example.com",
            "phone" to "1234567890"
        )

        val result = firebaseAccount.addUserToFirestore(uid, userInfo)
        assertTrue(result.isSuccess)
    }

    @Test
    fun testSingOut() = runBlocking {
        firebaseAccount.singOut()
        val currentUser = firebaseAccount.auth.currentUser
        assertNull(currentUser)
    }

    @Test
    fun testSendPasswordResetEmail() = runBlocking {
        val email = "test@example.com"
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        firebaseAccount.sendPasswordResetEmail(email, context) { success, errorMessage ->
            assertTrue(success)
        }
    }

    @Test
    fun testAddBankCard() = runBlocking {
        val userId = "testUID"
        val card = Card("1234567890123456", "12/24", "123")

        firebaseAccount.addBankCard(userId, card) { success ->
            assertTrue(success)
        }
    }
}
