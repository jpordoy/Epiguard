package com.epilabs.epiguard.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.epilabs.epiguard.models.SeizureModel

object SeizureDAO {
    const val TABLE_NAME = "tblSeizure"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            seizureID INTEGER PRIMARY KEY AUTOINCREMENT,
            userID INTEGER NOT NULL,
            timestamp TEXT NOT NULL,
            seizureType TEXT,
            duration INTEGER,
            description TEXT,
            triggers TEXT,
            medicationTaken TEXT,
            postSeizureSymptoms TEXT,
            notes TEXT,
            FOREIGN KEY (userID) REFERENCES ${UserDAO.TABLE_NAME}(userID) ON DELETE CASCADE
        )
    """

    fun insertSeizure(db: SQLiteDatabase, seizure: SeizureModel): Long {
        val values = ContentValues().apply {
            put("userID", seizure.userId)
            put("timestamp", seizure.timestamp)
            put("seizureType", seizure.seizureType)
            put("duration", seizure.duration)
            put("description", seizure.description)
            put("triggers", seizure.triggers)
            put("medicationTaken", seizure.medicationTaken)
            put("postSeizureSymptoms", seizure.postSeizureSymptoms)
            put("notes", seizure.notes)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllSeizures(db: SQLiteDatabase, userId: Int): List<SeizureModel> {
        val list = mutableListOf<SeizureModel>()
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
                SeizureModel(
                    seizureID = cursor.getInt(cursor.getColumnIndexOrThrow("seizureID")),
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                    timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp")) ?: "",
                    seizureType = cursor.getString(cursor.getColumnIndexOrThrow("seizureType")),
                    duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration")).takeIf { !cursor.isNull(cursor.getColumnIndexOrThrow("duration")) },
                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    triggers = cursor.getString(cursor.getColumnIndexOrThrow("triggers")),
                    medicationTaken = cursor.getString(cursor.getColumnIndexOrThrow("medicationTaken")),
                    postSeizureSymptoms = cursor.getString(cursor.getColumnIndexOrThrow("postSeizureSymptoms")),
                    notes = cursor.getString(cursor.getColumnIndexOrThrow("notes"))
                )
            )
        }
        cursor.close()
        return list
    }

    fun getSeizureById(db: SQLiteDatabase, seizureId: Int): SeizureModel? {
        val cursor = db.query(
            TABLE_NAME,
            null,
            "seizureID = ?",
            arrayOf(seizureId.toString()),
            null,
            null,
            null
        )
        var seizure: SeizureModel? = null
        if (cursor.moveToFirst()) {
            seizure = SeizureModel(
                seizureID = cursor.getInt(cursor.getColumnIndexOrThrow("seizureID")),
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp")) ?: "",
                seizureType = cursor.getString(cursor.getColumnIndexOrThrow("seizureType")),
                duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration")).takeIf { !cursor.isNull(cursor.getColumnIndexOrThrow("duration")) },
                description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                triggers = cursor.getString(cursor.getColumnIndexOrThrow("triggers")),
                medicationTaken = cursor.getString(cursor.getColumnIndexOrThrow("medicationTaken")),
                postSeizureSymptoms = cursor.getString(cursor.getColumnIndexOrThrow("postSeizureSymptoms")),
                notes = cursor.getString(cursor.getColumnIndexOrThrow("notes"))
            )
        }
        cursor.close()
        return seizure
    }

    fun updateSeizure(db: SQLiteDatabase, seizure: SeizureModel): Int {
        val values = ContentValues().apply {
            put("userID", seizure.userId)
            put("timestamp", seizure.timestamp)
            put("seizureType", seizure.seizureType)
            put("duration", seizure.duration)
            put("description", seizure.description)
            put("triggers", seizure.triggers)
            put("medicationTaken", seizure.medicationTaken)
            put("postSeizureSymptoms", seizure.postSeizureSymptoms)
            put("notes", seizure.notes)
        }
        return db.update(
            TABLE_NAME,
            values,
            "seizureID = ?",
            arrayOf(seizure.seizureID.toString())
        )
    }

    fun deleteSeizure(db: SQLiteDatabase, seizureId: Int): Int {
        return db.delete(
            TABLE_NAME,
            "seizureID = ?",
            arrayOf(seizureId.toString())
        )
    }
}