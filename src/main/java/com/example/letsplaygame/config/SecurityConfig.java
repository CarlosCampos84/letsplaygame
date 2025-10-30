package com.example.letsplaygame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // só login e estáticos são públicos
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                        // proteger POST que altera estado (redundante, mas explícito)
                        .requestMatchers(HttpMethod.POST, "/do-something").authenticated()
                        // qualquer outra rota (ex.: "/") exige login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                         // endpoint de logout (POST)
                        .logoutSuccessUrl("/login?logout")            // volta pro login
                        .invalidateHttpSession(true)                  // invalida sessão
                        .deleteCookies("JSESSIONID")                  // remove cookie de sessão
                )
                .csrf(Customizer.withDefaults()); // CSRF ligado
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
                .password("{noop}123456")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
