package com.library_management_thymeleaf_CRUD.inventory_management.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("USER")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("USER", "LIBRARIAN")
                .build();

        UserDetails susen = User.builder()
                .username("susen")
                .password("{noop}test123")
                .roles("USER","LIBRARIAN","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john,mary,susen);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests(customizer ->
                customizer
                        .requestMatchers("/books/delete").hasRole("ADMIN")
                        .requestMatchers("/books/showFormForAdd","/books/saveBook","/books/showFormForUpdate").
                            hasRole("LIBRARIAN")
                        .requestMatchers("/books/list","/books/search","/books/").hasRole("USER")

                        .anyRequest().authenticated()
                );


        http.formLogin(form ->
                form.
                        loginPage("/showLoginForm")
                        .loginProcessingUrl("/authenticateTheUser")
                        .failureUrl("/showLoginForm?error")
                        .defaultSuccessUrl("/books/",true)
                        .permitAll()
        );

        http.logout(logout ->
                logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/showLoginForm?logout")
                        .permitAll()
        );

        http.exceptionHandling(exception ->
                exception
                        .accessDeniedPage("/access-denied")

        );
        return http.build();
    }

}












