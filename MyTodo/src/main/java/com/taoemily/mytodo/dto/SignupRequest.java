package com.taoemily.mytodo.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotNull
    String username;
    @NotNull
    String useremail;
    @NotNull
    String password;
}
