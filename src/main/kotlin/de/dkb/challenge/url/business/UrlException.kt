package de.dkb.challenge.url.business

class UrlNotFoundException(hash: String) : RuntimeException("$hash not found!")

class UrlInvalidException(url: String) : RuntimeException("$url is not valid!")