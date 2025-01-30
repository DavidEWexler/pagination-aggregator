package org.dwexler.contacts.service;

import java.util.function.Function;
import org.dwexler.contacts.client.ExternalContact;
import org.dwexler.contacts.config.ExternalApiConfig;
import org.dwexler.contacts.model.Contact;

public class ContactTransform implements Function<ExternalContact, Contact> {
  private final String source;

  public ContactTransform(ExternalApiConfig config) {
    source = config.getApiSource();
  }

  @Override
  public Contact apply(ExternalContact externalContact) {
    return new Contact(externalContact.getId(),
        externalContact.getName(),
        externalContact.getEmail(),
        source,
        externalContact.getCreatedAt(),
        externalContact.getUpdatedAt()
    );
  }
}
