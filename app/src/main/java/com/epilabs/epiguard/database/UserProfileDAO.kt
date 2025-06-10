package com.epilabs.epiguard.database

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import com.epilabs.epiguard.models.UserProfileModel

object UserProfileDAO {
    const val TABLE_NAME = "tblUserProfiles"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            profile_id INTEGER PRIMARY KEY AUTOINCREMENT,
            userID INTEGER NOT NULL,
            full_name TEXT,
            phone TEXT,
            date_of_birth TEXT,
            profile_image TEXT,
            bio TEXT,
            FOREIGN KEY (userID) REFERENCES ${UserDAO.TABLE_NAME}(userID) ON DELETE CASCADE
        )
    """

    fun insertProfile(db: SQLiteDatabase, profile: UserProfileModel): Long {
        val values = ContentValues().apply {
            put("userID", profile.userId)
            put("full_name", profile.fullName)
            put("phone", profile.phone)
            put("date_of_birth", profile.dateOfBirth)
            put("profile_image", profile.profileImage)
            put("bio", profile.bio)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllProfiles(db: SQLiteDatabase, userId: Int): List<UserProfileModel> {
        val list = mutableListOf<UserProfileModel>()
        val cursor = db.query(
            TABLE_NAME,
            null,
            "userID = ?",
            arrayOf(userId.toString()),
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            list.add(
                UserProfileModel(
                    profileId = cursor.getInt(cursor.getColumnIndexOrThrow("profile_id")),
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                    fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name")),
                    phone = cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                    dateOfBirth = cursor.getString(cursor.getColumnIndexOrThrow("date_of_birth")),
                    profileImage = cursor.getString(cursor.getColumnIndexOrThrow("profile_image")),
                    bio = cursor.getString(cursor.getColumnIndexOrThrow("bio"))
                )
            )
        }
        cursor.close()
        return list
    }

    fun getProfileById(db: SQLiteDatabase, profileId: Int): UserProfileModel? {
        val cursor = db.query(
            TABLE_NAME,
            null,
            "profile_id = ?",
            arrayOf(profileId.toString()),
            null,
            null,
            null
        )
        var profile: UserProfileModel? = null
        if (cursor.moveToFirst()) {
            profile = UserProfileModel(
                profileId = cursor.getInt(cursor.getColumnIndexOrThrow("profile_id")),
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
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

    fun updateProfile(db: SQLiteDatabase, profile: UserProfileModel): Int {
        val values = ContentValues().apply {
            put("userID", profile.userId)
            put("full_name", profile.fullName)
            put("phone", profile.phone)
            put("date_of_birth", profile.dateOfBirth)
            put("profile_image", profile.profileImage)
            put("bio", profile.bio)
        }
        return db.update(
            TABLE_NAME,
            values,
            "profile_id = ?",
            arrayOf(profile.profileId.toString())
        )
    }

    fun deleteProfile(db: SQLiteDatabase, profileId: Int): Int {
        return db.delete(
            TABLE_NAME,
            "profile_id = ?",
            arrayOf(profileId.toString())
        )
    }
}