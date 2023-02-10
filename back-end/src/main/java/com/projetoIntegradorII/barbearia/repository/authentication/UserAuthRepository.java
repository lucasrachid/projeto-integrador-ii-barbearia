package com.projetoIntegradorII.barbearia.repository.authentication;

import com.projetoIntegradorII.barbearia.entity.autenticathion.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByUsernameEquals(String username);

}
