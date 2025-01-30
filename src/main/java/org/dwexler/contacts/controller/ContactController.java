package org.dwexler.contacts.controller;

import java.util.List;
import java.util.Objects;
import org.dwexler.contacts.model.Contact;
import org.dwexler.contacts.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  private final ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = Objects.requireNonNull(contactService);
  }

  @GetMapping("/contacts")
  public List<Contact> getAllContacts() {
    return contactService.getContacts();
  }
}
