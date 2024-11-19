    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests((authz) -> authz
                    // need this in order to get proper errors instead of 401!!
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/settings/**").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .requestMatchers("/actuator/**").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(filterChainExceptionHandler(), JwtAuthenticationFilter.class)
            .addFilterAfter(claimsFilter(), JwtAuthenticationFilter.class)
            .authenticationProvider(authProvider)
            .userDetailsService(userDetailsService)
            .exceptionHandling((handler) -> handler.authenticationEntryPoint(jwtEntryPoint))
            .build();
    }
