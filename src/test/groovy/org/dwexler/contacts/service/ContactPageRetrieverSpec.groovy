package org.dwexler.contacts.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.dwexler.contacts.client.ExternalContact
import org.dwexler.contacts.config.ExternalApiConfig
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.nio.charset.StandardCharsets

class ContactPageRetrieverSpec extends Specification {
  def "should retrieve contacts page successfully"() {
    given: "A JSON Response from the API"
    def restTemplate = Mock(RestTemplate)
    def config = Mock(ExternalApiConfig)
    config.getApiUri() >> "https://unit.test/contacts"
    config.getApiToken() >> "spock"
    def mockContacts = loadJson("response.json", ExternalContact[].class)
    def headers = new HttpHeaders()
    headers.add("Total-Pages", "50")
    headers.add("Total-Count", "1000")

    def retriever = new ContactPageRetriever(config, restTemplate)

    when:
    def result = retriever.getPage(1)

    then:
    1 * restTemplate.exchange("https://unit.test/contacts?page=1", HttpMethod.GET, _, _) >>
        new ResponseEntity<>(mockContacts, headers, HttpStatus.OK)
    result.contacts.size() == mockContacts.length
    result.totalPages == 50
    result.totalCount == 1000
  }

  def "should error if correct headers aren't present"() {
    given: "A JSON Response from the API"
    def restTemplate = Mock(RestTemplate)
    def config = Mock(ExternalApiConfig)
    config.getApiUri() >> "https://unit.test/contacts"
    config.getApiToken() >> "spock"
    def mockContacts = loadJson("response.json", ExternalContact[].class)
    def headers = new HttpHeaders()

    def retriever = new ContactPageRetriever(config, restTemplate)

    when:
    retriever.getPage(1)

    then:
    1 * restTemplate.exchange("https://unit.test/contacts?page=1", HttpMethod.GET, _, _) >>
        new ResponseEntity<>(mockContacts, headers, HttpStatus.OK)
    thrown NullPointerException
  }

  private static <T> T loadJson(String filename, Class<T> clazz) {
    ObjectMapper objectMapper = new ObjectMapper()
    objectMapper.registerModule(new JavaTimeModule())  // Support Java 8 Date/Time API

    String json = StreamUtils.copyToString(
        new ClassPathResource(filename).getInputStream(), StandardCharsets.UTF_8
    )
    return objectMapper.readValue(json, clazz)
  }
}
