package com.projetoIntegradorII.barbearia.dto.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthDTO {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private List<RolesDTO> roles;

    private String createdBy;
    private String lastModifiedBy;
    private Boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
