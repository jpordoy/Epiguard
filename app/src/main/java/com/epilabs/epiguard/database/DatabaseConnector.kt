package com.epilabs.epiguard.database

import UserProfileDAO
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.epilabs.epiguard.models.UserProfileModel

class DatabaseConnector(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(UserProfileDAO.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${UserProfileDAO.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "epiguardDb"
        private const val DATABASE_VERSION = 1
    }

    // Wrapper methods:

    fun insertUserProfile(profile: UserProfileModel): Long {
        val db = writableDatabase
        return UserProfileDAO.insertProfile(db, profile)
    }

    fun getAllUserProfiles(): List<UserProfileModel> {
        val db = readableDatabase
        return UserProfileDAO.getAllProfiles(db)
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
