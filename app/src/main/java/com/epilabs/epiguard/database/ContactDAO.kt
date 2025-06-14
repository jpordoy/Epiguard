package com.epilabs.epiguard.database

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import com.epilabs.epiguard.models.ContactModel

object ContactDAO {
    const val TABLE_NAME = "tblContact"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            contactID INTEGER PRIMARY KEY AUTOINCREMENT,
            userID INTEGER NOT NULL,
            firstname TEXT,
            lastname TEXT,
            contact TEXT,
            profile_image TEXT,
            alertType TEXT,
            medicalExperience TEXT,
            status TEXT,
            primaryCarer TEXT,
            timestamp TEXT,
            FOREIGN KEY (userID) REFERENCES ${UserDAO.TABLE_NAME}(userID) ON DELETE CASCADE
        )
    """

    fun insertContact(db: SQLiteDatabase, contact: ContactModel): Long {
        val values = ContentValues().apply {
            put("userID", contact.userID)
            put("firstname", contact.firstname)
            put("lastname", contact.lastname)
            put("contact", contact.contact)
            put("profile_image", contact.profileImage)
            put("alertType", contact.alertType)
            put("medicalExperience", contact.medicalExperience)
            put("status", contact.status)
            put("primaryCarer", contact.primaryCarer)
            put("timestamp", contact.timestamp)
        }
        return db.insert(TABLE_NAME, null, values)
    }


    fun getAllContacts(db: SQLiteDatabase, contactID: Int): List<ContactModel> {
        val list = mutableListOf<ContactModel>()
        val cursor = db.query(
            TABLE_NAME,
            null,
            "contactID = ?",
            arrayOf(contactID.toString()),
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            list.add(
                ContactModel(
                    contactID = cursor.getInt(cursor.getColumnIndexOrThrow("contactID")),
                    userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                    firstname = cursor.getString(cursor.getColumnIndexOrThrow("firstname")) ?: "",
                    lastname = cursor.getString(cursor.getColumnIndexOrThrow("lastname")) ?: "",
                    contact = cursor.getString(cursor.getColumnIndexOrThrow("contact")) ?: "",
                    profileImage = cursor.getString(cursor.getColumnIndexOrThrow("profile_image")) ?: "",
                    alertType = cursor.getString(cursor.getColumnIndexOrThrow("alertType")) ?: "",
                    medicalExperience = cursor.getString(cursor.getColumnIndexOrThrow("medicalExperience")) ?: "",
                    status = cursor.getString(cursor.getColumnIndexOrThrow("status")) ?: "",
                    primaryCarer = cursor.getString(cursor.getColumnIndexOrThrow("primaryCarer")) ?: "",
                    timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp")) ?: ""
                )
            )
        }
        cursor.close()
        return list
    }

    fun getContactById(db: SQLiteDatabase, contactID: Int): ContactModel? {
        val cursor = db.query(
            TABLE_NAME,
            null,
            "contactID = ?",
            arrayOf(contactID.toString()),
            null,
            null,
            null
        )
        var contact: ContactModel? = null
        if (cursor.moveToFirst()) {
            contact = ContactModel(
                contactID = cursor.getInt(cursor.getColumnIndexOrThrow("contactID")),
                userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID")),
                firstname = cursor.getString(cursor.getColumnIndexOrThrow("firstname")) ?: "",
                lastname = cursor.getString(cursor.getColumnIndexOrThrow("lastname")) ?: "",
                contact = cursor.getString(cursor.getColumnIndexOrThrow("contact")) ?: "",
                profileImage = cursor.getString(cursor.getColumnIndexOrThrow("profile_image")) ?: "",
                alertType = cursor.getString(cursor.getColumnIndexOrThrow("alertType")) ?: "",
                medicalExperience = cursor.getString(cursor.getColumnIndexOrThrow("medicalExperience")) ?: "",
                status = cursor.getString(cursor.getColumnIndexOrThrow("status")) ?: "",
                primaryCarer = cursor.getString(cursor.getColumnIndexOrThrow("primaryCarer")) ?: "",
                timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp")) ?: ""
            )
        }
        cursor.close()
        return contact
    }

    fun updateContact(db: SQLiteDatabase, contact: ContactModel): Int {
        val values = ContentValues().apply {
            put("firstname", contact.firstname)
            put("lastname", contact.lastname)
            put("contact", contact.contact)
            put("profile_image", contact.profileImage)
            put("alertType", contact.alertType)
            put("medicalExperience", contact.medicalExperience)
            put("status", contact.status)
            put("primaryCarer", contact.primaryCarer)
            put("timestamp", contact.timestamp)
        }
        return db.update(
            TABLE_NAME,
            values,
            "contactID = ?",
            arrayOf(contact.contactID.toString())
        )
    }

    fun deleteContact(db: SQLiteDatabase, contactID: Int): Int {
        return db.delete(
            TABLE_NAME,
            "contactID = ?",
            arrayOf(contactID.toString())
        )
    }
}