package org.dwexler.contacts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.dwexler.contacts.client.ContactPageResponse;
import org.dwexler.contacts.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
  private final ContactPageRetriever pageRetriever;
  private final ContactTransform transform;

  @Autowired
  public ContactService(ContactPageRetriever pageRetriever, ContactTransform transform) {
    this.pageRetriever = Objects.requireNonNull(pageRetriever);
    this.transform = Objects.requireNonNull(transform);
  }

  public List<Contact> getContacts() {
    ContactPageResponse page1 = pageRetriever.getPage(1);

    List<Contact> contacts = new ArrayList<>(page1.getTotalCount());
    contacts.addAll(page1.getContacts().stream().map(transform).toList());

    IntStream.rangeClosed(2, page1.getTotalPages()).mapToObj(pageRetriever::getPage)
        .flatMap(page -> page.getContacts().stream())
        .map(transform)
        .forEach(contacts::add);

    return contacts;
  }
}