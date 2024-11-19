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
}
