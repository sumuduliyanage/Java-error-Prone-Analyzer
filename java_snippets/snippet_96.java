    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(withDefaults())
//            .httpBasic(Customizer.withDefaults())  don't use it!
            .formLogin(withDefaults());

        http.authorizeHttpRequests(
          c -> c
                .requestMatchers(HttpMethod.POST, "/api/v1/test-admin").hasRole(String.valueOf(ADMIN))
                .requestMatchers(HttpMethod.GET, "/api/v1/test-user").hasRole(String.valueOf(USER))
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/questions/").permitAll()
                .requestMatchers(HttpMethod.POST, "/error").permitAll() // important!
                .requestMatchers("/swagger-ui/**", "/openapi.yml", "/swagger-ui.html" ).permitAll()
                .anyRequest().authenticated()
        );
        http.csrf(
                c -> c.disable()
        );
        return http.build();
    }

