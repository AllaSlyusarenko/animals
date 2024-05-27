package ru.mts.hw_3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.mts.hw_3.security.jwt.AuthTokenFilter;
import ru.mts.hw_3.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    @Lazy
    private UserService userDetailsService;

    @Autowired
    AuthTokenFilter authenticationJwtTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(@Autowired PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

//    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(new AntPathRequestMatcher("/animals/one")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/animals/all")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/animals/add")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/animals/delete/*")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/animals/signup")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/animals/signin")).permitAll()
                                .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, @Autowired PasswordEncoder passwordEncoder) throws Exception {
        http.csrf(csrf -> csrf.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth ->
                auth.requestMatchers(new AntPathRequestMatcher("/animals/signup")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/animals/signin")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/animals/all")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/animals/one")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/animals/delete/*")).authenticated()
                        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider(passwordEncoder));

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}