package com.projetoIntegradorII.barbearia.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO {
    String actualPassword;
    String newPassword;
    String repeatNewPassword;
}
