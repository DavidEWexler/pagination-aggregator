package org.dwexler.contacts.service

import org.dwexler.contacts.client.ExternalContact
import org.dwexler.contacts.config.ExternalApiConfig
import spock.lang.Specification

import java.time.OffsetDateTime

class ContactTransformSpec extends Specification {
  def"Performing a Transform Works"() {
    given:
    def config = Mock(ExternalApiConfig)
    config.getApiSource() >> "SPOCK_TEST"
    def transform = new ContactTransform(config)
    def externalContact = new ExternalContact()
    def now = OffsetDateTime.now()
    externalContact.setId("1")
    externalContact.setName("Unit Test")
    externalContact.setEmail("whatever@unit.test")
    externalContact.setCreatedAt(now)
    externalContact.setUpdatedAt(now)

    when:
    def contact = transform.apply(externalContact)

    then:
    contact.id == 1
    contact.name == "Unit Test"
    contact.email == "whatever@unit.test"
    contact.source == "SPOCK_TEST"
    contact.createdAt == now
    contact.updatedAt == now
  }
}
