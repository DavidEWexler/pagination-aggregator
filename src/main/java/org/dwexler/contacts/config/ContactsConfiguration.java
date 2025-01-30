package org.dwexler.contacts.config;

import org.dwexler.contacts.service.ContactsPageRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ContactsConfiguration {

  @Bean
  public ExternalApiConfig externalApiConfig() {
    return new ExternalApiConfig("KENECT");
  }

  @Bean
  public ContactsPageRetriever contactsPageRetriever(ExternalApiConfig config) {
    return new ContactsPageRetriever(config, new RestTemplate());
  }
}
