package com.epilabs.epiguard.utils

import android.util.Log
import android.util.Patterns
import com.epilabs.epiguard.database.DatabaseConnector

object ValidationUtils {
    fun isValidEmail(email: String, dbHandler: DatabaseConnector): Boolean {
        if (email.isBlank()) {
            Log.w("ValidationUtils", "Email is empty")
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.w("ValidationUtils", "Invalid email format: $email")
            return false
        }
        if (dbHandler.isEmailTaken(email)) {
            Log.w("ValidationUtils", "Email already registered: $email")
            return false
        }
        return true
    }

    fun isValidUsername(username: String, dbHandler: DatabaseConnector): Boolean {
        if (username.isBlank()) {
            Log.w("ValidationUtils", "Username is empty")
            return false
        }
        if (username.length < 3) {
            Log.w("ValidationUtils", "Username too short: $username")
            return false
        }
        if (!username.matches("^[a-zA-Z0-9_]+$".toRegex())) {
            Log.w("ValidationUtils", "Invalid username characters: $username")
            return false
        }
        if (dbHandler.isUsernameTaken(username)) {
            Log.w("ValidationUtils", "Username already taken: $username")
            return false
        }
        return true
    }

    fun isValidPassword(password: String): Boolean {
        if (password.isBlank()) {
            Log.w("ValidationUtils", "Password is empty")
            return false
        }
        if (password.length < 8) {
            Log.w("ValidationUtils", "Password too short: ${password.length} characters")
            return false
        }
        if (!password.contains("[A-Z]".toRegex())) {
            Log.w("ValidationUtils", "Password missing uppercase letter")
            return false
        }
        if (!password.contains("[0-9]".toRegex())) {
            Log.w("ValidationUtils", "Password missing number")
            return false
        }
        return true
    }
}