package org.dwexler.contacts.controller;

import org.dwexler.contacts.config.ExternalApiConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
  private final ExternalApiConfig config;

  public ContactController(ExternalApiConfig config) {
    this.config = config;
  }

  @GetMapping("/contacts")
  public String getAllContacts() {
    return config.getApiSource();
  }
}
