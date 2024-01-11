package com.sparta.wangnyang.common.authority

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    private val allowedUrls = arrayOf("/", "/user/signup", "/user/login")
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain{
        http.httpBasic { it.disable() }
                .csrf { it.disable() }
                .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)
                .authorizeHttpRequests {
                    it.requestMatchers(*allowedUrls).permitAll()
                            .requestMatchers("/user/login","/user/signup").permitAll()
                            .anyRequest().authenticated()
//                            .anyRequest().authenticated()
                }


        return http.build();
    }

    @Bean
    fun passwordEncoder():PasswordEncoder= BCryptPasswordEncoder()

}