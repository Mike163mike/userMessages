package com.mike.usermessages.configuration;

import com.mike.usermessages.service.SecurityUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityUserService securityUserService;

    public SecurityConfig(SecurityUserService securityUserService) {
        this.securityUserService = securityUserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
//                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/test/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/message/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/user/**").hasRole("ADMIN"))
                .formLogin(withDefaults())
                .logout((logout) ->
                        logout
                                .logoutSuccessUrl("/login"));

        return http.build();

    }

// Users saved In memory case
//    @Bean
//    public
//    UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.builder()
//                        .username("user")
//                        .password("{bcrypt}$2a$12$yWKWEtHWjtSS.yOvM85h.O0taPtjly3UpbxcUPd6ZtsnmubwqYpAG") //100
//                        .roles("USER")
//                        .build();
//        UserDetails admin =
//                User.builder()
//                        .username("admin")
//                        .password("{bcrypt}$2a$12$yWKWEtHWjtSS.yOvM85h.O0taPtjly3UpbxcUPd6ZtsnmubwqYpAG")
//                        .roles("ADMIN", "USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

//
//
//    //Кратко with jdbc
//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    //DAO authentication provider

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(securityUserService);
        return authenticationProvider;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }
}


