package com.bej.service;

import com.bej.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String, String> generateToken(User user);
}
