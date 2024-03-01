package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.domain.Role;
import com.dmalex.ordermanagementsystem.service.AuthService;
import com.dmalex.ordermanagementsystem.service.PersonService;
import com.dmalex.ordermanagementsystem.web.controller.AuthController;
import com.dmalex.ordermanagementsystem.web.dto.AuthRequest;
import com.dmalex.ordermanagementsystem.web.dto.PersonDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final PersonService personService;
    private final AuthService authService;

    @PostMapping("/register/user")
    @Override
    public ResponseEntity<?> registerUser(@Valid @RequestBody PersonDto personDto) {
        personDto.setRole(Role.ROLE_USER);
        return ResponseEntity.ok(personService.create(personDto));
    }

    @PostMapping("/register/admin")
    @Override
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody PersonDto personDto) {
        personDto.setRole(Role.ROLE_ADMIN);
        return ResponseEntity.ok(personService.create(personDto));
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> login(AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }
}
