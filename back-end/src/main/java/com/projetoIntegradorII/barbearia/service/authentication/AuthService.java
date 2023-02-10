package com.projetoIntegradorII.barbearia.service.authentication;


import com.projetoIntegradorII.barbearia.dto.InfoDTO;
import com.projetoIntegradorII.barbearia.dto.authentication.UserAuthDTO;

public interface AuthService {

    InfoDTO login(UserAuthDTO userDTO);



}
