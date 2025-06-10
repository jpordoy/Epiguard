package com.epilabs.epiguard.utils

import java.util.Properties
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object EmailSender {
    suspend fun sendOTP(email: String, otp: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val properties = Properties().apply {
                put("mail.smtp.host", "smtp.gmail.com")
                put("mail.smtp.port", "587")
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.debug", "true") // Enable debug logging for troubleshooting
            }
            val session = Session.getInstance(properties, object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication() = javax.mail.PasswordAuthentication(
                    "jamiepordoy@gmail.com", // Your Gmail address
                    "eipifgqeilnhwcvs" // Replace with the 16-digit App Password (no spaces)
                )
            })
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress("jamiepordoy@gmail.com")) // Match the authentication email
                addRecipient(Message.RecipientType.TO, InternetAddress(email))
                subject = "Your OTP Code"
                setText("Your OTP code is: $otp")
            }
            Transport.send(message)
            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception("Failed to send OTP: ${e.message}"))
        }
    }

    fun generateOTP(): String {
        return (100000..999999).random().toString()
    }
}