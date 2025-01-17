package de.dkb.challenge.url.api

import de.dkb.challenge.url.business.UrlInvalidException
import de.dkb.challenge.url.business.UrlNotFoundException
import de.dkb.challenge.url.business.UrlService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/url")
class UrlController(
    private val urlService: UrlService
) {

    @PostMapping("/shorten")
    fun shortenUrl(
        //could be extracted to a dto data class for a cleaner seperation
        @RequestBody url: String
    ): ResponseEntity<String> {
        val shortenedUrl = urlService.shortenUrl(url)
        return ResponseEntity.ok(shortenedUrl)
    }

    @GetMapping("/{shortenedUrl}")
    fun getUrl(@PathVariable shortenedUrl: String): ResponseEntity<String> {
        val url = urlService.getUrl(shortenedUrl)
        return ResponseEntity.ok(url)
    }

    @ExceptionHandler(UrlInvalidException::class)
    fun handleUrlInvalidException(e: UrlInvalidException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(UrlNotFoundException::class)
    fun handleUrlNotFoundException(e: UrlNotFoundException): ResponseEntity<String> {
        return ResponseEntity.notFound().build()
    }
}