package org.dwexler.contacts.config;

public class ExternalApiConfig {
  private final String apiToken;
  private final String apiUri;
  private final String apiSource;

  public ExternalApiConfig(String prefix) {
    this.apiToken = System.getenv(prefix + "_API_TOKEN");
    this.apiUri = System.getenv(prefix + "_API_URI");
    this.apiSource = System.getenv(prefix + "_API_SOURCE");
  }

  public String getApiToken() {
    return apiToken;
  }

  public String getApiUri() {
    return apiUri;
  }

  public String getApiSource() {
    return apiSource;
  }
}
