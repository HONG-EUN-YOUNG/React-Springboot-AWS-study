package com.example.Todo.config;

import com.example.Todo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // CORS 설정 활성화
        .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
        .csrf(csrf -> csrf.disable()) // CSRF 비활성화
        .httpBasic(basic -> basic.disable()) // HTTP Basic 인증 비활성화
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 설정
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/auth/**").permitAll() // 인증 필요 없는 경로
            .anyRequest().authenticated() // 나머지 경로는 인증 필요
        );

    // 필터 등록
    // jwtAuthenticationFilter를 CorsFilter 이후에 실행하라
    http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);

    return http.build();
  }
}