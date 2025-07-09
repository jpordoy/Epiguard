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
            email TEXT,
            profile_image TEXT,
            alertType TEXT,
            about TEXT,
            status TEXT,
            epilepsyFirstAid TEXT,
            cPR TEXT,
            mentalHealthFirstAid TEXT,
            primaryCarer TEXT,
            timestamp TEXT,
            relationship TEXT,
            FOREIGN KEY (userID) REFERENCES ${UserDAO.TABLE_NAME}(userID) ON DELETE CASCADE
        )
    """

    fun insertContact(db: SQLiteDatabase, contact: ContactModel): Long {
        val values = ContentValues().apply {
            put("userID", contact.userID)
            put("firstname", contact.firstname)
            put("lastname", contact.lastname)
            put("contact", contact.contact)
            put("email", contact.email)
            put("profile_image", contact.profileImage)
            put("alertType", contact.alertType)
            put("epilepsyFirstAid", contact.epilepsyFirstAid)
            put("cPR", contact.cPR)
            put("mentalHealthFirstAid", contact.mentalHealthFirstAid)
            put("about", contact.about)
            put("status", contact.status)
            put("primaryCarer", contact.primaryCarer)
            put("relationship", contact.relationship)
            put("timestamp", contact.timestamp)

        }
        return db.insert(TABLE_NAME, null, values)
    }


    fun getAllContacts(db: SQLiteDatabase, userID: Int): List<ContactModel> {
        val list = mutableListOf<ContactModel>()
        val cursor = db.query(
            TABLE_NAME,
            null,
            "userID = ?",
            arrayOf(userID.toString()),
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
                    email = cursor.getString(cursor.getColumnIndexOrThrow("email")) ?: "",
                    profileImage = cursor.getString(cursor.getColumnIndexOrThrow("profile_image")),
                    alertType = cursor.getString(cursor.getColumnIndexOrThrow("alertType")) ?: "",
                    about = cursor.getString(cursor.getColumnIndexOrThrow("about")) ?: "",
                    epilepsyFirstAid = cursor.getString(cursor.getColumnIndexOrThrow("epilepsyFirstAid")) ?: "",
                    cPR = cursor.getString(cursor.getColumnIndexOrThrow("cPR")) ?: "",
                    mentalHealthFirstAid = cursor.getString(cursor.getColumnIndexOrThrow("mentalHealthFirstAid")) ?: "",
                    status = cursor.getString(cursor.getColumnIndexOrThrow("status")) ?: "",
                    primaryCarer = cursor.getString(cursor.getColumnIndexOrThrow("primaryCarer")) ?: "",
                    timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp")) ?: "",
                    relationship = cursor.getString(cursor.getColumnIndexOrThrow("relationship")) ?: ""

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
                email = cursor.getString(cursor.getColumnIndexOrThrow("email")) ?: "",
                contact = cursor.getString(cursor.getColumnIndexOrThrow("contact")) ?: "",
                profileImage = cursor.getString(cursor.getColumnIndexOrThrow("profile_image")) ?: "",
                alertType = cursor.getString(cursor.getColumnIndexOrThrow("alertType")) ?: "",
                about = cursor.getString(cursor.getColumnIndexOrThrow("about")) ?: "",
                epilepsyFirstAid = cursor.getString(cursor.getColumnIndexOrThrow("epilepsyFirstAid")) ?: "",
                cPR = cursor.getString(cursor.getColumnIndexOrThrow("cPR")) ?: "",
                mentalHealthFirstAid = cursor.getString(cursor.getColumnIndexOrThrow("mentalHealthFirstAid")) ?: "",
                status = cursor.getString(cursor.getColumnIndexOrThrow("status")) ?: "",
                primaryCarer = cursor.getString(cursor.getColumnIndexOrThrow("primaryCarer")) ?: "",
                relationship = cursor.getString(cursor.getColumnIndexOrThrow("relationship")) ?: "",
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
            put("email", contact.email)
            put("profile_image", contact.profileImage)
            put("alertType", contact.alertType)
            put("about", contact.about)
            put("epilepsyFirstAid", contact.epilepsyFirstAid)
            put("cPR", contact.cPR)
            put("mentalHealthFirstAid", contact.mentalHealthFirstAid)
            put("status", contact.status)
            put("primaryCarer", contact.primaryCarer)
            put("relationship", contact.relationship)
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