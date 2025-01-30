package org.dwexler.contacts.service;

import java.util.Arrays;
import java.util.Objects;
import org.dwexler.contacts.client.ContactPageResponse;
import org.dwexler.contacts.client.ExternalContact;
import org.dwexler.contacts.config.ExternalApiConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ContactPageRetriever {

  private final String uri;
  private final String token;
  private final RestTemplate restTemplate;

  public ContactPageRetriever(ExternalApiConfig config, RestTemplate restTemplate) {
    this.uri = config.getApiUri();
    this.token = config.getApiToken();
    this.restTemplate = restTemplate;
  }

  public ContactPageResponse getPage(int page) {
    String url = UriComponentsBuilder.fromUriString(uri)
        .queryParam("page", page)
        .toUriString();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "application/json");
    headers.add("Authorization", "Bearer " + token);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<ExternalContact[]> response = restTemplate.exchange(
        url, HttpMethod.GET, entity, ExternalContact[].class
    );
    if (response.getStatusCode() == HttpStatus.OK) {
      HttpHeaders responseHeaders = response.getHeaders();
      int totalPages = Integer.parseInt(
          Objects.requireNonNull(responseHeaders.getFirst("Total-Pages")));
      int totalCount = Integer.parseInt(
          Objects.requireNonNull(responseHeaders.getFirst("Total-Count")));

      return new ContactPageResponse(Arrays.asList(Objects.requireNonNull(response.getBody())),
          totalPages, totalCount);

    } else {
      throw new RuntimeException("Failed to fetch contacts: " + response.getStatusCode());
    }
  }
}
