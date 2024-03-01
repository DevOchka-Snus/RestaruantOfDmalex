package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.web.dto.AuthRequest;
import com.dmalex.ordermanagementsystem.web.dto.PersonDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<?> registerUser(PersonDto personDto);
    ResponseEntity<?> registerAdmin(PersonDto personDto);
    ResponseEntity<?> login(AuthRequest request);
}
