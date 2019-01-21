package fr.wildcodeschool.emailbuilder;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class EmailBuilderTest {

  // --------------------------------------------------------------------------
  // TEST : EmailBuilder.Builder class
  // --------------------------------------------------------------------------

  @Test
  public void emailBuilder_GetEmailSimple_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder
      .Builder()
      .setUserName("name")
      .setDomain("email")
      .setTld("com")
      .build();
    assertEquals("name@email.com", emailBuilder.getEmail());
  }

  @Test
  public void emailBuilder_GetEmailWithSubDomain_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder
      .Builder()
      .setUserName("name")
      .setDomain("email")
      .setSubDomain("co")
      .setTld("uk")
      .build();
    assertEquals("name@email.co.uk", emailBuilder.getEmail());
  }

  @Test
  public void emailBuilder_GetEmailNoUsername_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder
      .Builder()
      .setDomain("email")
      .setSubDomain("co")
      .setTld("uk")
      .build();
    assertEquals("@email.co.uk", emailBuilder.getEmail());
  }

  @Test
  public void emailBuilder_GetEmailNoDomain_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder
      .Builder()
      .setUserName("name")
      .setSubDomain("co")
      .setTld("uk")
      .build();
    assertEquals("name@co.uk", emailBuilder.getEmail());
  }

  @Test
  public void emailBuilder_GetEmailNoTld_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder
      .Builder()
      .setUserName("name")
      .setDomain("email")
      .setSubDomain("co")
      .build();
    assertEquals("name@email.co", emailBuilder.getEmail());
  }

  @Test
  public void emailBuilder_GetEmailEmptyString_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder
      .Builder()
      .setUserName("")
      .setDomain("")
      .setSubDomain("")
      .setTld("")
      .build();
    assertEquals("", emailBuilder.getEmail());
  }

  @Test
  public void emailBuilder_GetNullEmail_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder.Builder().build();
    assertEquals("", emailBuilder.getEmail());
  }

  // --------------------------------------------------------------------------
  // TEST : isValidEmail method
  // --------------------------------------------------------------------------

  @Test
  public void emailBuilder_isValidEmailSimple_ReturnsTrue() {
    assertTrue(EmailBuilder.isValidEmail("nathan@gmail.com"));
  }

  @Test
  public void emailBuilder_isValidEmailWithSubDomain_ReturnsTrue() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("name")
            .setDomain("email")
            .setTld("com")
            .build();
    assertTrue(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidEmailNoUsername_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setDomain("email")
            .setSubDomain("co")
            .setTld("uk")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidEmailNoDomain_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("name")
            .setTld("uk")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidEmailNoTld_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("name")
            .setDomain("email")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidEmailEmptyString_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder.Builder().build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidNullEmail_ReturnsFalse() {
    assertFalse(EmailBuilder.isValidEmail(null));
  }

  @Test
  public void emailBuilder_isValidWrongName_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("*$£%ù^¨&é\"'()°-è_çà§æ«€¶ŧ←↓→øþ¨¤@ßðđŋħłµł»¢“")
            .setDomain("email")
            .setTld("com")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidNameOutOfBound_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName(String.join("", Collections.nCopies(256, "a")))
            .setDomain("email")
            .setTld("com")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidWrongDomain_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setDomain("*$£%ù^¨&é\"'()°-è_çà§æ«€¶ŧ←↓→øþ¨¤@ßðđŋħłµł»¢“")
            .setUserName("user")
            .setTld("com")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidDomainOutOfBound_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("user")
            .setDomain(String.join("", Collections.nCopies(65, "a")))
            .setTld("com")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidWrongSubDomain_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setDomain("email")
            .setUserName("user")
            .setSubDomain("\"*$£%ù^¨&é\\\"'()°-è_çà§æ«€¶")
            .setTld("com")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidSubDomainOutOfBound_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("user")
            .setDomain("email")
            .setSubDomain(String.join("", Collections.nCopies(65, "a")))
            .setTld("com")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidWrongTld_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("user")
            .setDomain("email")
            .setSubDomain(String.join("", Collections.nCopies(25, "a")))
            .setTld("\"*$£%ù^¨&é\\\"'()°-è_çà§æ«€¶")
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }

  @Test
  public void emailBuilder_isValidTldOutOfBound_ReturnsFalse() {
    EmailBuilder emailBuilder = new EmailBuilder
            .Builder()
            .setUserName("user")
            .setDomain("email")
            .setSubDomain("uk")
            .setTld(String.join("", Collections.nCopies(65, "a")))
            .build();
    assertFalse(EmailBuilder.isValidEmail(emailBuilder.getEmail()));
  }
}