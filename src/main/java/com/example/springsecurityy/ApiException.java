package com.example.springsecurityy;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
