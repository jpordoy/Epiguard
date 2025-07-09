package com.epilabs.epiguard.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.epilabs.epiguard.models.ContactModel
import com.epilabs.epiguard.models.RawDataModel
import com.epilabs.epiguard.models.SeizureModel
import com.epilabs.epiguard.models.UserProfileModel
import com.epilabs.epiguard.models.SeizureWithRawData

class DatabaseConnector(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "epiguardDb"
        private const val DATABASE_VERSION = 8      // ← bumped from 5 ➜ 6
        private const val TAG = "DatabaseConnector"
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Creating database tables")
        db.execSQL(UserDAO.CREATE_TABLE)
        db.execSQL(UserProfileDAO.CREATE_TABLE)
        db.execSQL(ContactDAO.CREATE_TABLE)
        db.execSQL(RawDataDAO.CREATE_TABLE)
        db.execSQL(SeizureDAO.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "Upgrading database from version $oldVersion to $newVersion")
        db.execSQL("DROP TABLE IF EXISTS ${SeizureDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${RawDataDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${ContactDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${UserProfileDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${UserDAO.TABLE_NAME}")
        onCreate(db)
    }

    fun registerUser(email: String, username: String, password: String): Long {
        Log.d(TAG, "Attempting to register user: email=$email, username=$username")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return -1
        }
        return try {
            val userId = UserDAO.insertUser(db, email, username, password)
            Log.d(TAG, "User insertion result: userId=$userId")
            userId
        } catch (e: Exception) {
            Log.e(TAG, "Registration exception: ${e.message}", e)
            -1
        } finally {
            db.close()
        }
    }

    fun testInsertUser(email: String, username: String, password: String): Long {
        Log.d(TAG, "Testing user insertion: email=$email, username=$username")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database for test: ${e.message}", e)
            return -1
        }
        return try {
            val result = UserDAO.insertUser(db, email, username, password)
            Log.d(TAG, "Test insertion result: $result")
            result
        } catch (e: Exception) {
            Log.e(TAG, "Test insertion exception: ${e.message}", e)
            -1
        } finally {
            db.close()
        }
    }

    fun debugCheckUsersTable(): Int {
        Log.d(TAG, "Checking users table")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return -1
        }
        val cursor = try {
            db.rawQuery("SELECT count(*) FROM ${UserDAO.TABLE_NAME}", null)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to query users table: ${e.message}", e)
            db.close()
            return -1
        }
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        Log.d(TAG, "Users table row count: $count")
        return count
    }

    fun verifyUser(email: String): Boolean {
        Log.d(TAG, "Verifying user: email=$email")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return false
        }
        return try {
            UserDAO.verifyUser(db, email)
        } catch (e: Exception) {
            Log.e(TAG, "Verification exception: ${e.message}", e)
            false
        } finally {
            db.close()
        }
    }

    fun signInUser(identifier: String, password: String): Int? {
        Log.d(TAG, "Signing in user: identifier=$identifier")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return null
        }
        return try {
            UserDAO.signInUser(db, identifier, password)
        } catch (e: Exception) {
            Log.e(TAG, "Sign-in exception: ${e.message}", e)
            null
        } finally {
            db.close()
        }
    }

    fun isEmailTaken(email: String): Boolean {
        Log.d(TAG, "Checking if email is taken: $email")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return false
        }
        val cursor = db.query(
            UserDAO.TABLE_NAME,
            arrayOf("email"),
            "email = ?",
            arrayOf(email),
            null,
            null,
            null
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        Log.d(TAG, "Email taken: $exists")
        return exists
    }

    fun isUsernameTaken(username: String): Boolean {
        Log.d(TAG, "Checking if username is taken: $username")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return false
        }
        val cursor = db.query(
            UserDAO.TABLE_NAME,
            arrayOf("username"),
            "username = ?",
            arrayOf(username),
            null,
            null,
            null
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        Log.d(TAG, "Username taken: $exists")
        return exists
    }

    fun insertUserProfile(profile: UserProfileModel): Long {
        Log.d(TAG, "Inserting user profile for userID=${profile.userId}")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return -1
        }
        return try {
            UserProfileDAO.insertProfile(db, profile)
        } catch (e: Exception) {
            Log.e(TAG, "Profile insertion exception: ${e.message}", e)
            -1
        } finally {
            db.close()
        }
    }

    fun getAllUserProfiles(userId: Int): List<UserProfileModel> {
        Log.d(TAG, "Getting all profiles for userID=$userId")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return emptyList()
        }
        return try {
            UserProfileDAO.getAllProfiles(db, userId)
        } catch (e: Exception) {
            Log.e(TAG, "Get profiles exception: ${e.message}", e)
            emptyList()
        } finally {
            db.close()
        }
    }

    fun getUserProfileById(profileId: Int): UserProfileModel? {
        Log.d(TAG, "Getting profile by ID=$profileId")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return null
        }
        return try {
            UserProfileDAO.getProfileById(db, profileId)
        } catch (e: Exception) {
            Log.e(TAG, "Get profile exception: ${e.message}", e)
            null
        } finally {
            db.close()
        }
    }

    fun updateUserProfile(profile: UserProfileModel): Int {
        Log.d(TAG, "Updating profile for profileID=${profile.profileId}")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return 0
        }
        return try {
            UserProfileDAO.updateProfile(db, profile)
        } catch (e: Exception) {
            Log.e(TAG, "Profile update exception: ${e.message}", e)
            0
        } finally {
            db.close()
        }
    }

    fun deleteUserProfile(profileId: Int): Int {
        Log.d(TAG, "Deleting profile with ID=$profileId")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return 0
        }
        return try {
            UserProfileDAO.deleteProfile(db, profileId)
        } catch (e: Exception) {
            Log.e(TAG, "Profile deletion exception: ${e.message}", e)
            0
        } finally {
            db.close()
        }
    }

    fun insertContact(contact: ContactModel): Long {
        Log.d(TAG, "Inserting contact for userID=${contact.userID}")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return -1
        }
        return try {
            ContactDAO.insertContact(db, contact)
        } catch (e: Exception) {
            Log.e(TAG, "Contact insertion exception: ${e.message}", e)
            -1
        } finally {
            db.close()
        }
    }

    fun getAllContacts(userId: Int): List<ContactModel> {
        Log.d(TAG, "Getting all contacts for userID=$userId")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return emptyList()
        }
        return try {
            ContactDAO.getAllContacts(db, userId)
        } catch (e: Exception) {
            Log.e(TAG, "Get contacts exception: ${e.message}", e)
            emptyList()
        } finally {
            db.close()
        }
    }

    fun getContactById(contactID: Int): ContactModel? {
        Log.d(TAG, "Getting contact by ID=$contactID")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return null
        }
        return try {
            ContactDAO.getContactById(db, contactID)
        } catch (e: Exception) {
            Log.e(TAG, "Get contact exception: ${e.message}", e)
            null
        } finally {
            db.close()
        }
    }

    fun updateContact(contact: ContactModel): Int {
        Log.d(TAG, "Updating contact with ID=${contact.contactID}")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return 0
        }
        return try {
            ContactDAO.updateContact(db, contact)
        } catch (e: Exception) {
            Log.e(TAG, "Contact update exception: ${e.message}", e)
            0
        } finally {
            db.close()
        }
    }

    fun deleteContact(contactID: Int): Int {
        Log.d(TAG, "Deleting contact with ID=$contactID")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return 0
        }
        return try {
            ContactDAO.deleteContact(db, contactID)
        } catch (e: Exception) {
            Log.e(TAG, "Contact deletion exception: ${e.message}", e)
            0
        } finally {
            db.close()
        }
    }

    fun insertRawData(rawData: RawDataModel): Long {
        Log.d(TAG, "Inserting raw data for userID=${rawData.userId}")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return -1
        }
        return try {
            RawDataDAO.insertRawData(db, rawData)
        } catch (e: Exception) {
            Log.e(TAG, "Raw data insertion exception: ${e.message}", e)
            -1
        } finally {
            db.close()
        }
    }

    fun getAllRawData(userId: Int): List<RawDataModel> {
        Log.d(TAG, "Getting all raw data for userID=$userId")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return emptyList()
        }
        return try {
            RawDataDAO.getAllRawData(db, userId)
        } catch (e: Exception) {
            Log.e(TAG, "Get raw data exception: ${e.message}", e)
            emptyList()
        } finally {
            db.close()
        }
    }

    fun insertSeizure(seizure: SeizureModel): Long {
        Log.d(TAG, "Inserting seizure for userID=${seizure.userId}")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return -1
        }
        return try {
            SeizureDAO.insertSeizure(db, seizure)
        } catch (e: Exception) {
            Log.e(TAG, "Seizure insertion exception: ${e.message}", e)
            -1
        } finally {
            db.close()
        }
    }

    fun getAllSeizures(userId: Int): List<SeizureModel> {
        Log.d(TAG, "Getting all seizures for userID=$userId")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return emptyList()
        }
        return try {
            SeizureDAO.getAllSeizures(db, userId)
        } catch (e: Exception) {
            Log.e(TAG, "Get seizures exception: ${e.message}", e)
            emptyList()
        } finally {
            db.close()
        }
    }

    fun getSeizureById(seizureID: Int): SeizureModel? {
        Log.d(TAG, "Getting seizure by ID=$seizureID")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return null
        }
        return try {
            SeizureDAO.getSeizureById(db, seizureID)
        } catch (e: Exception) {
            Log.e(TAG, "Get seizure exception: ${e.message}", e)
            null
        } finally {
            db.close()
        }
    }

    fun updateSeizure(seizure: SeizureModel): Int {
        Log.d(TAG, "Updating seizure with ID=${seizure.seizureID}")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return 0
        }
        return try {
            SeizureDAO.updateSeizure(db, seizure)
        } catch (e: Exception) {
            Log.e(TAG, "Seizure update exception: ${e.message}", e)
            0
        } finally {
            db.close()
        }
    }

    fun deleteSeizure(seizureID: Int): Int {
        Log.d(TAG, "Deleting seizure with ID=$seizureID")
        val db = try {
            writableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open writable database: ${e.message}", e)
            return 0
        }
        return try {
            SeizureDAO.deleteSeizure(db, seizureID)
        } catch (e: Exception) {
            Log.e(TAG, "Seizure deletion exception: ${e.message}", e)
            0
        } finally {
            db.close()
        }
    }

    fun getUserProfileWithUserDetails(userId: Int): UserProfileWithDetails? {
        Log.d(TAG, "Getting user profile with details for userID=$userId")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return null
        }
        val cursor = try {
            db.rawQuery(
                """
                SELECT u.userID, u.email, u.username, u.password, p.profile_id, p.full_name, p.phone, p.date_of_birth, p.profile_image, p.bio
                FROM ${UserDAO.TABLE_NAME} u
                LEFT JOIN ${UserProfileDAO.TABLE_NAME} p ON u.userID = p.userID
                WHERE u.userID = ?
                """,
                arrayOf(userId.toString())
            )
        } catch (e: Exception) {
            Log.e(TAG, "User profile details query failed: ${e.message}", e)
            db.close()
            return null
        }
        var profile: UserProfileWithDetails? = null
        if (cursor.moveToFirst()) {
            profile = UserProfileWithDetails(
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                email = cursor.getString(cursor.getColumnIndexOrThrow("email")),
                username = cursor.getString(cursor.getColumnIndexOrThrow("username")),
                password = cursor.getString(cursor.getColumnIndexOrThrow("password")),
                profileId = cursor.getInt(cursor.getColumnIndexOrThrow("profile_id")).takeIf { !cursor.isNull(cursor.getColumnIndexOrThrow("profile_id")) } ?: 0,
                fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name")),
                phone = cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                dateOfBirth = cursor.getString(cursor.getColumnIndexOrThrow("date_of_birth")),
                profileImage = cursor.getString(cursor.getColumnIndexOrThrow("profile_image")),
                bio = cursor.getString(cursor.getColumnIndexOrThrow("bio"))
            )
        }
        cursor.close()
        db.close()
        Log.d(TAG, "User profile details result: ${profile?.userId}")
        return profile
    }

    fun getAllSeizuresWithRawData(userId: Int): List<SeizureWithRawData> {
        Log.d(TAG, "Getting all seizures with raw data for userID=$userId")
        val db = try {
            readableDatabase
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open readable database: ${e.message}", e)
            return emptyList()
        }
        val list = mutableListOf<SeizureWithRawData>()
        val cursor = try {
            db.rawQuery(
                """
                SELECT s.seizureID, s.userID, s.timestamp AS seizureTimestamp, s.seizureType, s.duration, 
                       s.description, s.triggers, s.medicationTaken, s.postSeizureSymptoms, s.notes,
                       r.rawDataId, r.timestamp AS rawDataTimestamp, r.classificationResult, 
                       r.numberOfClassifiedTimesteps, r.predictedClass
                FROM ${SeizureDAO.TABLE_NAME} s
                LEFT JOIN ${RawDataDAO.TABLE_NAME} r ON s.seizureID = r.seizureID
                WHERE s.userID = ?
                ORDER BY s.seizureTimestamp DESC
                """,
                arrayOf(userId.toString())
            )
        } catch (e: Exception) {
            Log.e(TAG, "Seizures with raw data query failed: ${e.message}", e)
            db.close()
            return emptyList()
        }
        while (cursor.moveToNext()) {
            list.add(
                SeizureWithRawData(
                    seizureID = cursor.getInt(cursor.getColumnIndexOrThrow("seizureID")),
                    userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                    seizureTimestamp = cursor.getString(cursor.getColumnIndexOrThrow("seizureTimestamp")) ?: "",
                    seizureType = cursor.getString(cursor.getColumnIndexOrThrow("seizureType")) ?: "",
                    duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration")).takeIf { !cursor.isNull(cursor.getColumnIndexOrThrow("duration")) },
                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    triggers = cursor.getString(cursor.getColumnIndexOrThrow("triggers")),
                    medicationTaken = cursor.getString(cursor.getColumnIndexOrThrow("medicationTaken")),
                    postSeizureSymptoms = cursor.getString(cursor.getColumnIndexOrThrow("postSeizureSymptoms")),
                    notes = cursor.getString(cursor.getColumnIndexOrThrow("notes")),
                    rawDataId = cursor.getInt(cursor.getColumnIndexOrThrow("rawDataId")).takeIf { !cursor.isNull(cursor.getColumnIndexOrThrow("rawDataId")) },
                    rawDataTimestamp = cursor.getString(cursor.getColumnIndexOrThrow("rawDataTimestamp")),
                    classificationResult = cursor.getString(cursor.getColumnIndexOrThrow("classificationResult")),
                    numberOfClassifiedTimesteps = cursor.getInt(cursor.getColumnIndexOrThrow("numberOfClassifiedTimesteps")).takeIf { !cursor.isNull(cursor.getColumnIndexOrThrow("numberOfClassifiedTimesteps")) },
                    predictedClass = cursor.getString(cursor.getColumnIndexOrThrow("predictedClass"))
                )
            )
        }
        cursor.close()
        db.close()
        Log.d(TAG, "Retrieved ${list.size} seizures with raw data")
        return list
    }
}

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