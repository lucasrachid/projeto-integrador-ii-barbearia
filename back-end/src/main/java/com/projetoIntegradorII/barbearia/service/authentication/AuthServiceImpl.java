package com.projetoIntegradorII.barbearia.service.authentication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoIntegradorII.barbearia.dto.InfoDTO;
import com.projetoIntegradorII.barbearia.dto.authentication.LoginDTO;
import com.projetoIntegradorII.barbearia.dto.authentication.RolesDTO;
import com.projetoIntegradorII.barbearia.dto.authentication.UserAuthDTO;
import com.projetoIntegradorII.barbearia.entity.autenticathion.UserAuth;
import com.projetoIntegradorII.barbearia.exception.InfoException;
import com.projetoIntegradorII.barbearia.repository.authentication.UserAuthRepository;
import com.projetoIntegradorII.barbearia.security.Jwt.JwtToken;
import com.projetoIntegradorII.barbearia.security.Jwt.JwtTokenUtil;
import com.projetoIntegradorII.barbearia.security.Payloads.Response.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final JwtTokenUtil jwtUtils;
    private final UserAuthRepository userAuthRepository;
    private final ObjectMapper objectMapper;

    @Override
    public InfoDTO login(UserAuthDTO userAuthDTO) {

        InfoDTO infoDTO = new InfoDTO();

        try {
            // Validações -> Mesmo que seja realizada no front, para que não ocorra alguma falha
            // E o usuário manipule os dados, é realizado a verificação no Back-End também.
            if (userAuthDTO.getPassword().equals("")) {
                throw new InfoException("MESSAGES.PASSWORD_REQUIRED", HttpStatus.BAD_REQUEST);
            }

            if (userAuthDTO.getUsername().equals("")) {
                throw new InfoException("MESSAGES.USERNAME_REQUIRED", HttpStatus.BAD_REQUEST);
            }

            if (userAuthDTO.getPassword().length() <= 5) {
                throw new InfoException("MESSAGES.PASSWORD_LENGHT_MIN", HttpStatus.BAD_REQUEST);
            }

            if (userAuthDTO.getPassword().length() >= 100) {
                throw new InfoException("MESSAGES.PASSWORD_LENGHT_MAX", HttpStatus.BAD_REQUEST);
            }

            Optional<UserAuth> user = userAuthRepository.findByUsernameEquals(userAuthDTO.getUsername());

            if (user.isEmpty()) {
                throw new InfoException("MESSAGES.USERNAME_NOT_FOUND", HttpStatus.NOT_FOUND);
            }

            boolean matches = BCrypt.checkpw(userAuthDTO.getPassword(), user.get().getPassword());
            if (!matches) {
                throw new InfoException("MESSAGES.WRONG_PASSWORD", HttpStatus.UNAUTHORIZED);
            }

            List<RolesDTO> roles = objectMapper.convertValue(user.get().getRoles(), new TypeReference<List<RolesDTO>>() {
            });

            JwtToken jwtToken = new JwtToken();
            jwtToken.setId(user.get().getId().toString());
            jwtToken.setEmail(user.get().getEmail());
            jwtToken.setUsername(user.get().getUsername());
            jwtToken.setName(user.get().getName());

            String jwt = jwtUtils.generateJwtToken(jwtToken);

            JwtResponse jwtResponse = JwtResponse.builder()
                .accessToken(jwt)
                .tokenType("Bearer")
                .userId(user.get().getId())
                .expiresIn(jwtUtils.getExpirationSecondsFromToken(jwt))
                .build();

            LoginDTO loginDto = LoginDTO.builder()
                .jwt(jwtResponse)
                .roles(roles)
                .build();

            infoDTO.setMessage("MESSAGES.LOGIN_SUCESS");
            infoDTO.setStatus(HttpStatus.OK);
            infoDTO.setObject(loginDto);

            return infoDTO;

        } catch (InfoException e) {
            e.printStackTrace();
            infoDTO.setSuccess(false);
            infoDTO.setStatus(HttpStatus.BAD_REQUEST);
            infoDTO.setMessage(e.getMessage());
            return infoDTO;
        } catch (Exception e) {
            e.printStackTrace();
            infoDTO.setSuccess(false);
            infoDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            infoDTO.setMessage("MESSAGES.ERROR_LOGIN");
            return infoDTO;
        }
    }


}
