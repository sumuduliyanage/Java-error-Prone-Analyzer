@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class OAuth2Config {

    @Bean
    @ConditionalOnMissingBean
    public ClientRegistrationRepository clientRegistrationRepository(
            OAuth2ClientProperties oAuth2ClientProperties) {
        var clientRegistrations =
                List.copyOf(
                        new OAuth2ClientPropertiesMapper(oAuth2ClientProperties)
                                .asClientRegistrations()
                                .values());
        return new InMemoryClientRegistrationRepository(clientRegistrations);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(
            OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }
}
