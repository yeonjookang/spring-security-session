package com.spring_security.project.security.configs;

import jakarta.websocket.Encoder;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static UserDetailsManager users(){
        String password = passwordEncoder().encode("1111");

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}1111")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}1111")
                .roles("ADMIN,MANAGER,USER")
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password("{noop}1111")
                .roles("MANAGER,USER")
                .build();

        return new InMemoryUserDetailsManager(user,manager,admin);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //정적 파일들은 보안 필터를 거치지 않고 통과되게 하기(css,js,images)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authz->authz
                        .requestMatchers("/","/users").permitAll()
                        .requestMatchers("/mypage").hasRole("USER")
                        .requestMatchers("/messages").hasRole("MANAGER")
                        .requestMatchers("/config").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );

        http
                .formLogin(formLogin -> formLogin
                        .permitAll());

        return http.build();
    }
}
