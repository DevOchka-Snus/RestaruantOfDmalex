package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.domain.Person;
import com.dmalex.ordermanagementsystem.web.dto.AuthRequest;
import com.dmalex.ordermanagementsystem.web.dto.AuthResponse;
import com.dmalex.ordermanagementsystem.web.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    ResponseEntity<Person> registerUser(@Valid @RequestBody PersonDto personDto);

    ResponseEntity<Person> registerAdmin(@Valid @RequestBody PersonDto personDto);

    ResponseEntity<AuthResponse> login(AuthRequest request);
}
