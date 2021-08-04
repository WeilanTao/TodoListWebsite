package com.taoemily.mytodo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RefreshTokenRequest {
    @NotBlank
    private String useremail;
    @NotBlank
    private String refreshtoken;
}
