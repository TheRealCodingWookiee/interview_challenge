package de.dkb.challenge.url.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.dkb.challenge.url.business.UrlService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus.OK

@ExtendWith(MockKExtension::class)
class UrlControllerTest {
 @InjectMockKs lateinit var sut: UrlController
 @RelaxedMockK lateinit var urlService: UrlService

  @Test
  fun `should return shorten url from url service` () {
   val url = "www.google.com"
   val expectedShortenUrl = "1234"

   every { urlService.shortenUrl(url) } returns expectedShortenUrl

   val response = sut.shortenUrl(url)

   assertThat(response.statusCode).isEqualTo(OK)
   assertThat(response.body).isEqualTo(expectedShortenUrl)
  }

  @Test
  fun `should return url for given shorten url` () {
   val shortenedUrl = "1234"
   val expectedUrl = "www.google.de"

   every { urlService.getUrl(shortenedUrl) } returns expectedUrl

   val response = sut.getUrl(shortenedUrl)

   assertThat(response.statusCode).isEqualTo(OK)
   assertThat(response.body).isEqualTo(expectedUrl)
  }
 }