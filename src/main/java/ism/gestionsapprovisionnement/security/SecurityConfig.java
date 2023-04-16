package ism.gestionsapprovisionnement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     @Autowired
      UserDetailsService service;
      @Autowired
       PasswordEncoder passwordEncoder;
 /*
    @Bean
    public InMemoryUserDetailsManager userDetailsMemoiryManager(){
        UserDetails user= User.withDefaultPasswordEncoder()
                .username("client")
                .password("passer")
                .roles("Client")
                .build();
        UserDetails user1= User.withDefaultPasswordEncoder()
                .username("admin")
                .password("passer")
                .roles("Client","Admin")
                .build();
        return new InMemoryUserDetailsManager(user,user1);
    }

  */
 @Bean
 public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider authenticationProvider=
             new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(service);
         authenticationProvider.setPasswordEncoder(passwordEncoder);
         return authenticationProvider;
 }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeHttpRequests()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/admin/**").hasAuthority("Admin")
                .requestMatchers("/client/**").hasAuthority("Client")
                .anyRequest()
                .authenticated();
                 http.exceptionHandling().accessDeniedPage("/403");
                return http.build();
    }

}
