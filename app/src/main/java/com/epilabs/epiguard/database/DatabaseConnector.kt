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
}