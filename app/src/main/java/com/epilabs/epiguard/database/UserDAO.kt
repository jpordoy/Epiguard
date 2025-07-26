package com.epilabs.epiguard.database

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.util.Log
import org.mindrot.jbcrypt.BCrypt

object UserDAO {
    const val TABLE_NAME = "tblUsers"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            userID INTEGER PRIMARY KEY AUTOINCREMENT,
            email TEXT NOT NULL UNIQUE,
            username TEXT NOT NULL UNIQUE,
            password TEXT NOT NULL,
            isVerified INTEGER DEFAULT 0
        )
    """

    fun insertUser(db: SQLiteDatabase, email: String, username: String, password: String): Long {
        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        val values = ContentValues().apply {
            put("email", email)
            put("username", username)
            put("password", hashedPassword)
            put("isVerified", 0)
        }
        return try {
            val result = db.insertOrThrow(TABLE_NAME, null, values)
            Log.d("UserDAO", "User inserted successfully: email=$email, result=$result")
            result
        } catch (e: Exception) {
            Log.e("UserDAO", "Constraint violation: ${e.message}")
            -1
        }
    }

    fun verifyUser(db: SQLiteDatabase, email: String): Boolean {
        val values = ContentValues().apply {
            put("isVerified", 1)
        }
        return try {
            val rowsAffected = db.update(TABLE_NAME, values, "email = ?", arrayOf(email))
            Log.d("UserDAO", "Verify user: email=$email, rowsAffected=$rowsAffected")
            rowsAffected > 0
        } catch (e: Exception) {
            Log.e("UserDAO", "Verify user failed: ${e.message}")
            false
        }
    }

    fun signInUser(db: SQLiteDatabase, identifier: String, password: String): Int? {
        val cursor = try {
            db.query(
                TABLE_NAME,
                arrayOf("userID", "isVerified", "password"),
                "(email = ? OR username = ?)",
                arrayOf(identifier, identifier),
                null,
                null,
                null
            )
        } catch (e: Exception) {
            Log.e("UserDAO", "Sign-in query failed: ${e.message}")
            return null
        }
        return try {
            if (cursor.moveToFirst()) {
                val userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID"))
                val isVerified = cursor.getInt(cursor.getColumnIndexOrThrow("isVerified"))
                val storedPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"))
                cursor.close()
                if (isVerified == 1 && BCrypt.checkpw(password, storedPassword)) {
                    Log.d("UserDAO", "Sign-in successful: identifier=$identifier, userId=$userId")
                    userId
                } else {
                    Log.d("UserDAO", "Sign-in failed: isVerified=$isVerified")
                    null
                }
            } else {
                cursor.close()
                Log.d("UserDAO", "Sign-in failed: No user found for identifier=$identifier")
                null
            }
        } catch (e: Exception) {
            cursor.close()
            Log.e("UserDAO", "Sign-in exception: ${e.message}")
            null
        }
    }

    fun getUserFirstName(db: SQLiteDatabase, userId: Int): String? {
        val cursor = try {
            db.query(
                TABLE_NAME,
                arrayOf("username"),
                "userID = ?",
                arrayOf(userId.toString()),
                null,
                null,
                null
            )
        } catch (e: Exception) {
            Log.e("UserDAO", "Failed to fetch username: ${e.message}")
            return null
        }
        return try {
            if (cursor.moveToFirst()) {
                cursor.getString(cursor.getColumnIndexOrThrow("username"))
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("UserDAO", "Error fetching username: ${e.message}")
            null
        } finally {
            cursor.close()
        }
    }
}