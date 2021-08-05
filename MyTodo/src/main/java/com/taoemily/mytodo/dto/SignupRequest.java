package com.taoemily.mytodo.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    String username;
    @NotBlank
    String useremail;
    @NotBlank
    String password;
    @NotBlank
    Boolean isAdmin;
}
