package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.web.security.PersonTokenProvider;
import com.dmalex.ordermanagementsystem.service.AuthService;
import com.dmalex.ordermanagementsystem.service.PersonService;
import com.dmalex.ordermanagementsystem.web.dto.AuthRequest;
import com.dmalex.ordermanagementsystem.web.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PersonService personService;
    private final PersonTokenProvider personTokenProvider;

    @Override
    public AuthResponse login(AuthRequest request) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        if (authentication == null) {
            throw new IllegalStateException("login error");
        }
        System.out.println(authentication.isAuthenticated());
        var person = personService.getByLogin(request.getLogin());
        AuthResponse response = new AuthResponse();
        response.setPersonToken(personTokenProvider.createPersonToken(person.getLogin(), person.getPassword(), person.getRole()));
        return response;
    }
}
