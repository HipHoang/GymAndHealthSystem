/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hmh.filters.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 *
 * @author hieph
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.hmh.controllers",
    "com.hmh.repositories",
    "com.hmh.services"
})
public class SpringSecurityConfigs {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .securityMatcher("/api/**")
        .csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/login", "/api/users").permitAll()
            .requestMatchers(
                "/api/packages/**", "/api/feedbacks/**").permitAll()
            .requestMatchers("/api/secure/profile", "/api/secure/user/update/**").authenticated()
            .requestMatchers("/api/secure/health-profile/**", "/api/secure/my-packages/**", "/api/secure/progress/**",
                            "/api/secure/booking/**", "/api/secure/payments/**", "api/secure/stats/week", "api/secure/stats/month")
                .hasRole("USER")
            .requestMatchers("/api/secure/clients/**", "/api/secure/trainer-schedule/**", 
                        "/api/secure/trainer-progress/**"
            ).hasRole("TRAINER")
            .anyRequest().authenticated()
        )
        .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(e -> e
            .defaultAuthenticationEntryPointFor(
                (req, res, ex) -> {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.setContentType("application/json");
                    res.getWriter().write("{\"error\":\"Unauthorized\"}");
                },
                new AntPathRequestMatcher("/api/**")
            )
        );
    return http.build();
}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(c -> c.disable()).authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/home").authenticated()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/users", "/user-add-view", "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/training-packages", "/training-packages/**", "/statistics").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "/health-profiles/**",
                        "/userpackages/**",
                        "/bookings/**",
                        "/feedbacks/**",
                        "/training-progress/**",
                        "/payment-transactions/**"
                ).permitAll()
                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login").permitAll());

        return http.build();
    }


    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dy9g3l14t",
                        "api_key", "561892272853825",
                        "api_secret", "XOkm-geyI2L8xD6xazf68cnOPt0",
                        "secure", true));
        return cloudinary;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000/"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
