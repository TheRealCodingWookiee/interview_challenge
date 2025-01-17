package de.dkb.challenge.url.business

import de.dkb.challenge.url.persistence.UrlRepository
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URL
import java.security.MessageDigest

@Service
class UrlService(
    private val urlRepository: UrlRepository
) {
    fun shortenUrl(url: String): String {
        if (!isUrlValid(url)) {
            throw UrlInvalidException(url)
        }
        val shortenedUrl = hashUrl(url)
        urlRepository.save(shortenedUrl, url)
        return shortenedUrl
    }

    private fun isUrlValid(url: String): Boolean {
        return  try {
            URI.create(url).toURL()
            return true
        } catch (e: Exception) {
            false
        }
    }

    private fun hashUrl(url:String, length: Int = 6): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(url.toByteArray())
        val hex = bytes.joinToString("") { "%02x".format(it) }
        return hex.take(length)
    }


    fun getUrl(shortenedUrl: String): String {
        return urlRepository.find(shortenedUrl) ?: throw UrlNotFoundException(shortenedUrl)
    }

}