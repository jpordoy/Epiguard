package com.epilabs.epiguard.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.epilabs.epiguard.models.UserProfileModel

class DatabaseConnector(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "epiguardDb"
        private const val DATABASE_VERSION = 2 // Increment version to trigger onUpgrade
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(UserDAO.CREATE_TABLE)
        db.execSQL(UserProfileDAO.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${UserProfileDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${UserDAO.TABLE_NAME}")
        onCreate(db)
    }

    // User authentication methods
    fun registerUser(email: String, username: String, password: String): Long {
        val db = writableDatabase
        return UserDAO.insertUser(db, email, username, password)
    }

    fun verifyUser(email: String): Boolean {
        val db = writableDatabase
        return UserDAO.verifyUser(db, email)
    }

    fun signInUser(identifier: String, password: String): Int? {
        val db = readableDatabase
        return UserDAO.signInUser(db, identifier, password)
    }

    // User profile methods
    fun insertUserProfile(profile: UserProfileModel): Long {
        val db = writableDatabase
        return UserProfileDAO.insertProfile(db, profile)
    }

    fun getAllUserProfiles(userId: Int): List<UserProfileModel> {
        val db = readableDatabase
        return UserProfileDAO.getAllProfiles(db, userId)
    }

    fun getUserProfileById(profileId: Int): UserProfileModel? {
        val db = readableDatabase
        return UserProfileDAO.getProfileById(db, profileId)
    }

    fun updateUserProfile(profile: UserProfileModel): Int {
        val db = writableDatabase
        return UserProfileDAO.updateProfile(db, profile)
    }

    fun deleteUserProfile(profileId: Int): Int {
        val db = writableDatabase
        return UserProfileDAO.deleteProfile(db, profileId)
    }

    // New method to join tblUsers and tblUserProfiles
    fun getUserProfileWithUserDetails(userId: Int): UserProfileWithDetails? {
        val db = readableDatabase
        val cursor = db.rawQuery(
            """
            SELECT u.userID, u.email, u.username, u.password, p.profile_id, p.full_name, p.phone, p.date_of_birth, p.profile_image, p.bio
            FROM ${UserDAO.TABLE_NAME} u
            JOIN ${UserProfileDAO.TABLE_NAME} p ON u.userID = p.userID
            WHERE u.userID = ?
            """,
            arrayOf(userId.toString())
        )
        var profile: UserProfileWithDetails? = null
        if (cursor.moveToFirst()) {
            profile = UserProfileWithDetails(
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                email = cursor.getString(cursor.getColumnIndexOrThrow("email")),
                username = cursor.getString(cursor.getColumnIndexOrThrow("username")),
                password = cursor.getString(cursor.getColumnIndexOrThrow("password")),
                profileId = cursor.getInt(cursor.getColumnIndexOrThrow("profile_id")),
                fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name")),
                phone = cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                dateOfBirth = cursor.getString(cursor.getColumnIndexOrThrow("date_of_birth")),
                profileImage = cursor.getString(cursor.getColumnIndexOrThrow("profile_image")),
                bio = cursor.getString(cursor.getColumnIndexOrThrow("bio"))
            )
        }
        cursor.close()
        return profile
    }
}

// New data class to hold joined data
data class UserProfileWithDetails(
    val userId: Int,
    val email: String,
    val username: String,
    val password: String,
    val profileId: Int,
    val fullName: String?,
    val phone: String?,
    val dateOfBirth: String?,
    val profileImage: String?,
    val bio: String?
)