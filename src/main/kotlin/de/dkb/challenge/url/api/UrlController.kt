package de.dkb.challenge.url.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/url")
class UrlController {

    @PostMapping
    fun shortenUrl(
        //could be extracted to a dto data class for a cleaner seperation
        @RequestBody url: String
    ) {
        // TODO
    }

    @GetMapping
    fun getUrl() {
        // TODO
    }
}