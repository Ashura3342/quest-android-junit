package fr.wildcodeschool.emailbuilder;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class EmailBuilder {
  private String mUserName;
  private String mDomain;
  private String mSubDomain;
  private String mTld;

  /**
   * Email validation pattern.
   */
  private static final Pattern EMAIL_PATTERN = Pattern.compile(
    "[a-zA-Z0-9+._%\\-]{1,256}" +
      "@" +
      "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,64}" +
      "(" +
      "\\." +
      "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
      ")+"
  );

  private EmailBuilder() {}

  private boolean checkString(String value) {
    return value != null && !value.isEmpty();
  }

  /**
   * Formats and returns the build email
   * @return The build email address
   */
  public String getEmail() {
    return (checkString(mUserName) ? mUserName : "")
            + (checkString(mDomain) ? "@" + mDomain : "")
            + (checkString(mSubDomain) ? (checkString(mDomain) ? "." : "@") + mSubDomain : "")
            + (checkString(mTld) ? "." + mTld : "");
  }

  /**
   * Validates if the given input is a valid email address.
   *
   * @param email        The email to validate.
   * @return {@code true} if the input is a valid email. {@code false} otherwise.
   */
  public static boolean isValidEmail(CharSequence email) {
    return email != null && EMAIL_PATTERN.matcher(email).matches();
  }

  /**
   * Nested builder class
   */
  static class Builder {
    private EmailBuilder mEmailBuilder;

    public Builder() {
      mEmailBuilder = new EmailBuilder();
    }

    public Builder setUserName(String pUserName) {
      mEmailBuilder.mUserName = pUserName;
      return this;
    }

    public Builder setDomain(String pDomain) {
      mEmailBuilder.mDomain = pDomain;
      return this;
    }

    public Builder setSubDomain(String pSubDomain) {
      mEmailBuilder.mSubDomain = pSubDomain;
      return this;
    }

    public Builder setTld(String pTld) {
      mEmailBuilder.mTld = pTld;
      return this;
    }

    public EmailBuilder build() {
      return mEmailBuilder;
    }
  }
}
