package org.dwexler.contacts

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ContactsApplicationSpec extends Specification {

  def"String Startup Works"() {
    expect:
    true == true
  }
}
