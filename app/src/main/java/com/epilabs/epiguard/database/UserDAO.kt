package com.epilabs.epiguard.database

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
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
        return db.insert(TABLE_NAME, null, values)
    }

    fun verifyUser(db: SQLiteDatabase, email: String): Boolean {
        val values = ContentValues().apply {
            put("isVerified", 1)
        }
        val rowsAffected = db.update(TABLE_NAME, values, "email = ?", arrayOf(email))
        return rowsAffected > 0
    }

    fun signInUser(db: SQLiteDatabase, identifier: String, password: String): Int? {
        val cursor = db.query(
            TABLE_NAME,
            arrayOf("userID", "isVerified", "password"),
            "(email = ? OR username = ?)",
            arrayOf(identifier, identifier),
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID"))
            val isVerified = cursor.getInt(cursor.getColumnIndexOrThrow("isVerified"))
            val storedPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            cursor.close()
            if (isVerified == 1 && BCrypt.checkpw(password, storedPassword)) userId else null
        } else {
            cursor.close()
            null
        }
    }
}