package com.projetoIntegradorII.barbearia.controller.authentication;

import com.projetoIntegradorII.barbearia.entity.autenticathion.UserAuth;
import com.projetoIntegradorII.barbearia.service.authentication.AuthService;
import com.projetoIntegradorII.barbearia.dto.InfoDTO;
import com.projetoIntegradorII.barbearia.dto.authentication.UserAuthDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@Api(tags = {"Auth Controller"})
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    @ApiOperation(value = "Realizar o login", notes = "" +
        "Retorna informações de acesso do Usuário, junto com as permissões vinculadas a ele.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = InfoDTO.class),
        @ApiResponse(code = 401, message = "Não Autorizado"),
        @ApiResponse(code = 500, message = "Erro Interno de Servidor"),})
    public ResponseEntity<InfoDTO<UserAuth>> login(@RequestBody UserAuthDTO user) {
        InfoDTO<UserAuth> infoDTO = authService.login(user);
        return ResponseEntity.status(infoDTO.getStatus()).body(infoDTO);
    }


}
