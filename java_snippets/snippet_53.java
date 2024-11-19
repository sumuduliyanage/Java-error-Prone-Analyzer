  @SuppressWarnings("deprecation") // "password" provider is deprecated now
  private PasswordOAuth2AuthorizedClientProvider customExtendedPasswordProvider() {
    OAuth2PasswordGrantRequestEntityConverter extendedPasswordConverter =
        new OAuth2PasswordGrantRequestEntityConverter();
    extendedPasswordConverter.addHeadersConverter(
        ignored -> {
          HttpHeaders customHeaders = new HttpHeaders();
          customHeaders.add("idp", "99");
          return customHeaders;
        });

    DefaultPasswordTokenResponseClient extendedPasswordTokenResponseClient =
        new DefaultPasswordTokenResponseClient();
    extendedPasswordTokenResponseClient.setRequestEntityConverter(extendedPasswordConverter);

    PasswordOAuth2AuthorizedClientProvider extendedPasswordProvider =
        new PasswordOAuth2AuthorizedClientProvider();
    extendedPasswordProvider.setAccessTokenResponseClient(extendedPasswordTokenResponseClient);

    return extendedPasswordProvider;
  }
