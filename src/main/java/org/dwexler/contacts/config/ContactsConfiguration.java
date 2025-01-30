package org.dwexler.contacts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactsConfiguration {

  @Bean
  public ExternalApiConfig externalApiConfig() {
    return new ExternalApiConfig("KENECT");
  }
}
