package com.akeng.kintaimanagement.form;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class LoginForm {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
