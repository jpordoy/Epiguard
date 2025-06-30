package com.epilabs.epiguard.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.epilabs.epiguard.models.RawDataModel

object RawDataDAO {
    const val TABLE_NAME = "tblRawData"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            rawDataId INTEGER PRIMARY KEY AUTOINCREMENT,
            userID INTEGER NOT NULL,
            seizureID INTEGER,
            timestamp TEXT NOT NULL,
            classificationResult TEXT NOT NULL,
            numberOfClassifiedTimesteps INTEGER NOT NULL,
            predictedClass TEXT NOT NULL,
            FOREIGN KEY (userID) REFERENCES ${UserDAO.TABLE_NAME}(userID) ON DELETE CASCADE,
            FOREIGN KEY (seizureID) REFERENCES tblSeizure(seizureID) ON DELETE SET NULL
        )
    """

    fun insertRawData(db: SQLiteDatabase, rawData: RawDataModel): Long {
        val values = ContentValues().apply {
            put("userID", rawData.userId)
            put("seizureID", rawData.seizureID)
            put("timestamp", rawData.timestamp)
            put("classificationResult", rawData.classificationResult)
            put("numberOfClassifiedTimesteps", rawData.numberOfClassifiedTimesteps)
            put("predictedClass", rawData.predictedClass)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllRawData(db: SQLiteDatabase, userId: Int): List<RawDataModel> {
        val list = mutableListOf<RawDataModel>()
        val cursor = db.query(
            TABLE_NAME,
            null,
            "userID = ?",
            arrayOf(userId.toString()),
            null,
            null,
            "timestamp DESC" // Order by timestamp descending
        )
        while (cursor.moveToNext()) {
            list.add(
                RawDataModel(
                    rawDataId = cursor.getInt(cursor.getColumnIndexOrThrow("rawDataId")),
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                    seizureID = cursor.getInt(cursor.getColumnIndexOrThrow("seizureID")).takeIf { !cursor.isNull(cursor.getColumnIndexOrThrow("seizureID")) },
                    timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp")) ?: "",
                    classificationResult = cursor.getString(cursor.getColumnIndexOrThrow("classificationResult")) ?: "",
                    numberOfClassifiedTimesteps = cursor.getInt(cursor.getColumnIndexOrThrow("numberOfClassifiedTimesteps")),
                    predictedClass = cursor.getString(cursor.getColumnIndexOrThrow("predictedClass")) ?: ""
                )
            )
        }
        cursor.close()
        return list
    }
}