package org.dwexler.contacts.service

import org.dwexler.contacts.client.ContactPageResponse
import org.dwexler.contacts.client.ExternalContact
import org.dwexler.contacts.model.Contact
import spock.lang.Specification

class ContactServiceSpec extends Specification {
  def "should request the correct number of pages"() {
    given: "a service"
    def retriever = Mock(ContactPageRetriever)
    def transformer = Mock(ContactTransform)
    def service = new ContactService(retriever, transformer)

    when: "a call to get contacts is made"
    def result = service.getContacts()

    then: "the correct number of calls to get a page are called"
    1 * retriever.getPage(1) >> mockResponse(4, 80)
    1 * retriever.getPage(2) >> mockResponse(4, 80)
    1 * retriever.getPage(3) >> mockResponse(4, 80)
    1 * retriever.getPage(4) >> mockResponse(4, 80)
    0 * retriever.getPage(5)

    and: "the correct number of calls to transform are made"
    80 * transformer.apply(_) >> new Contact()
    result.size() == 80
  }

  private static ContactPageResponse mockResponse(int pages, int totalCount) {
    def contacts = new ArrayList<ExternalContact>()
    for (i in 0..<20) {
      contacts.add(new ExternalContact())
    }
    return new ContactPageResponse(contacts, pages, totalCount)
  }
}
