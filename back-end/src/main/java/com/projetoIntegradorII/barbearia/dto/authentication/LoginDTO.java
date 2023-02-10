package com.projetoIntegradorII.barbearia.dto.authentication;

import com.projetoIntegradorII.barbearia.security.Payloads.Response.JwtResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginDTO {
    private JwtResponse jwt;
    private List<RolesDTO> roles;
}
