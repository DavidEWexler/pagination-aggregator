package org.dwexler.contacts.config;

import org.dwexler.contacts.service.ContactPageRetriever;
import org.dwexler.contacts.service.ContactTransform;
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
  public ContactTransform getContactTransform(ExternalApiConfig config) {
    return new ContactTransform(config);
  }

  @Bean
  public ContactPageRetriever contactsPageRetriever(ExternalApiConfig config) {
    return new ContactPageRetriever(config, new RestTemplate());
  }
}
