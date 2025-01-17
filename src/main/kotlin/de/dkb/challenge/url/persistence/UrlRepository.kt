package de.dkb.challenge.url.persistence

import org.springframework.stereotype.Service

//This class, because of how it is structured with a Map, doesn't make sense to test because we would only test methods from the map class
@Service
class UrlRepository {
    //I chose map for simplicity reasons, here would be the place to use a database
    //in the repository I only define the operations on the database. I would create a seperate class 'UrlPersistenceService' that would handle
    //validation and error handling
    private val urls = mutableMapOf<String, String>()

    fun save(shortenedUrl: String, url: String) {
        urls[shortenedUrl] = url
    }

    fun find(shortenedUrl: String): String? {
        return urls[shortenedUrl]
    }
}