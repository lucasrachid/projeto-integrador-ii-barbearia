package com.projetoIntegradorII.barbearia.dto.authentication;

import lombok.Data;

@Data
public class ForgotPasswordDTO {
    private String identifier;
    private String token;
    private String urlResetPassword;
    private Long userId;
    private String password;
    private String passwordRepeat;
    private String recaptchaToken;
}
