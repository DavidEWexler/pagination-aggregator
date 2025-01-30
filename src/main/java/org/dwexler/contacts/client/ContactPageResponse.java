package org.dwexler.contacts.client;

import java.util.List;

public class ContactPageResponse {
  private final List<ExternalContact> contacts;
  private final int totalPages;
  private final int totalCount;

  public ContactPageResponse(List<ExternalContact> contacts, int totalPages, int totalCount) {
    this.contacts = contacts;
    this.totalPages = totalPages;
    this.totalCount = totalCount;
  }

  public List<ExternalContact> getContacts() {
    return contacts;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public int getTotalCount() {
    return totalCount;
  }
}
