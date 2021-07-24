package com.esgi.al2.projet.annuel.levelUp.user.service;

import com.esgi.al2.projet.annuel.levelUp.exception.ConflictException;
import com.esgi.al2.projet.annuel.levelUp.user.model.Login;
import com.esgi.al2.projet.annuel.levelUp.user.model.Session;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import com.esgi.al2.projet.annuel.levelUp.user.repository.SessionRepository;
import com.esgi.al2.projet.annuel.levelUp.user.repository.UserRepository;
import com.esgi.al2.projet.annuel.levelUp.user.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@AllArgsConstructor
@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;

    public HttpHeaders createSession(Login login) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getEmail(), login.getPassword()
        );
        var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        var token = tokenProvider.createToken(authentication);

        var user = userRepository.findByEmail(login.getEmail());
        if (user.isPresent()) {
            sessionRepository.findByUser(user.get()).ifPresent(sessionRepository::delete);

            sessionRepository.save(new Session(user.get().getId().toString(), null, token, user.get()));
        } else {
            throw new ResolutionException("there is no user registered with this email : " + login.getEmail());
        }

        var httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return httpHeaders;
    }

    public URI registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var existentUser = userRepository.findByEmail(user.getEmail());
        if (existentUser.isEmpty()) {
            var savedUser = userRepository.save(user);
            return fromPath("/api/users/").path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        } else {
            throw new ConflictException("this email : " + user.getEmail() + " is used by an other user");
        }
    }


}
