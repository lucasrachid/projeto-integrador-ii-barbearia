package com.projetoIntegradorII.barbearia.security.Jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtToken {
    private String id;
    private String username;
    private String email;
    private String name;
}
