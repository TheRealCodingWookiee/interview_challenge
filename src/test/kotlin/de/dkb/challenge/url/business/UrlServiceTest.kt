package de.dkb.challenge.url.business

import assertk.assertFailure
import assertk.assertions.isInstanceOf
import de.dkb.challenge.url.persistence.UrlRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UrlServiceTest {
  @InjectMockKs lateinit var sut: UrlService
  @RelaxedMockK lateinit var urlRepository: UrlRepository
  @RelaxedMockK lateinit var urlUtil: UrlUtil


      @Test
      fun `should shorten valid url` () {
       val url = "https://www.google.com"
       val expectedShorty = "123"
       every { urlUtil.hashUrl(url) } returns expectedShorty

       val actualShorty = sut.shortenUrl(url)

       verify { urlRepository.save("123", url) }
       assertThat(actualShorty).isEqualTo(expectedShorty)
      }

     @Test
     fun `should throw error if invalid url` () {
      val url = "i am not an url"

      assertFailure { sut.shortenUrl(url) }.isInstanceOf(UrlInvalidException::class)
     }

    @Test
    fun `should return the url if shortened url exists` () {
     val shortenedUrl = "123"
     val url = "https://www.google.com"
     every { urlRepository.find(shortenedUrl) } returns url

     val actualUrl = sut.getUrl(shortenedUrl)

     assertThat(actualUrl).isEqualTo(url)
    }

    @Test
    fun `should throw error if shortened url does not exist` () {
     val shortenedUrl = "123"
     every { urlRepository.find(shortenedUrl) } returns null

     assertFailure { sut.getUrl(shortenedUrl) }.isInstanceOf(UrlNotFoundException::class)
    }
 }