package com.hackaboss.agenciaTurismo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends SecurityConfigurerAdapter {



    @Bean
    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.POST}), "agency/hotels/new"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.PUT}), "agency/hotels/edit/{hotelId}"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.DELETE}), "agency/hotels/delete/{hotelId}"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.POST}), "agency/hotels/{hotelId}/rooms/new"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.PUT}), "/{hotelId}/rooms/edit/{roomId}"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.DELETE}), "/{hotelId}/rooms/delete/{roomId}"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.POST}), "agency/flights/new"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.PUT}), "agency/flights/edit/{flightId}"
                ).authenticated()
                .requestMatchers(
                        Arrays.toString(new HttpMethod[]{HttpMethod.DELETE}), "agency/flights/delete/{flightId}"
                ).authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .build();
    }
}