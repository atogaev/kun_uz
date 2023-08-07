package com.example.config;

//@Configuration
//@EnableWebSecurity
public class SpringSecurfityConfig {
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        // authentication (login,password)
//        String password = UUID.randomUUID().toString();
//        System.out.println("User Password mazgi: " + password);
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}12345")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("user")
//                .password("{noop}12345")
//                .roles("Admin")
//                .build();
//        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
//        return authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        /* authorization (ROLE) */
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/**").permitAll()
//                .requestMatchers("/api/v1/news/**").permitAll()
//                .requestMatchers("/api/v1/region/admin","/api/v1/region/admin/**").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and().formLogin();
//        return http.build();
//    }
}
