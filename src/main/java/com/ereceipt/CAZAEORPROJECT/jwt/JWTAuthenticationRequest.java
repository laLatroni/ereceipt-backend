package com.ereceipt.CAZAEORPROJECT.jwt;

import lombok.Data;
/**
 * FIELDS FOR THE LOG IN
 */

@Data
public class JWTAuthenticationRequest {
    private String userName;
    private String password;
}
