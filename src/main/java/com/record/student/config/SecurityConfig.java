package com.record.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }


    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User
        .builder()
        .username("himanshu")
        .password(passwordEncoder().encode("ayush@123"))
        .roles("ADMIN")
        .build();

        return new InMemoryUserDetailsManager(user1);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
        .formLogin(login ->{
            login
            .loginPage("/admin/login")
            .loginProcessingUrl("/admin/process-login")
            .defaultSuccessUrl("/admin/home",true)
            // .successForwardUrl("/admin/home")
            .permitAll();
        })

        .logout(logout->{
            logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/admin/login?logout=true")
            .permitAll();
            // .logoutSuccessUrl("/admin/login?logout=true");
        })

        .csrf(c -> c.disable())

        .cors(c -> c.disable())

        .authorizeHttpRequests(req ->{
            req
            .requestMatchers("/admin/**")
            .authenticated()
            .requestMatchers("/**")
            .permitAll();
        });
		
		return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
