package com.bej.service;

import com.bej.domain.Customer;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String, String> generateToken(Customer customer);
}
