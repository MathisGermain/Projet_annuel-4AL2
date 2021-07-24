package com.esgi.al2.projet.annuel.levelUp.configuration;


import com.esgi.al2.projet.annuel.levelUp.user.model.Role;
import com.esgi.al2.projet.annuel.levelUp.user.security.JWTFilter;
import com.esgi.al2.projet.annuel.levelUp.user.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    private static final String[] PUBLIC_ENDPOINTS = {
            "/api/auth/**"
    };

    private static final String[] ADMIN_ENDPOINTS = {
            "/api/admin/**"
    };

    private static final String[] USER_ENDPOINTS = {
            "/api/users/**",
            "/api/responses/**",
            "/api/exercises/**",
            "/api/comment/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .antMatchers(USER_ENDPOINTS).hasRole(Role.USER.toString())
                .antMatchers(ADMIN_ENDPOINTS).hasRole(Role.ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
