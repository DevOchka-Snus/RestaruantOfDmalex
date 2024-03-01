package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.web.dto.AuthRequest;
import com.dmalex.ordermanagementsystem.web.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(final AuthRequest request);
}
