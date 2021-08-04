package com.taoemily.mytodo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse implements Serializable {
    private String accessToken;
    private String username;
    private String useremail;
    private String refreshToken;
    private String role;
}
