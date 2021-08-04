package com.taoemily.mytodo.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {
    @NotNull
    private String userEmail;
    @NotNull
    private String passWord;

}
