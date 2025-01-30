package org.dwexler.contacts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
  @GetMapping("/contacts")
  public String getAllContacts() {
    return "Hello World";
  }
}
