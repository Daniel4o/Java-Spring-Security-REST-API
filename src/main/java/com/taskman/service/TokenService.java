package com.taskman.service;

import org.springframework.security.core.Authentication;

public interface TokenService {
    public String generateToken(Authentication authentication);
}
