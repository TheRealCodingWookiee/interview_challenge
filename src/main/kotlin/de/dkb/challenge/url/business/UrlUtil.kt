package de.dkb.challenge.url.business

import java.security.MessageDigest

//extracted for better testability
class UrlUtil {
    fun hashUrl(url:String, length: Int = 6): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(url.toByteArray())
        val hex = bytes.joinToString("") { "%02x".format(it) }
        return hex.take(length)
    }
}