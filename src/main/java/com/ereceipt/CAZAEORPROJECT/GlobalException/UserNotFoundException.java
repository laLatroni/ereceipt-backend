package com.ereceipt.CAZAEORPROJECT.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super("BAWAL PO");
    }
    public ResponseEntity<String> toResponseEntity() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessage());
    }

}
