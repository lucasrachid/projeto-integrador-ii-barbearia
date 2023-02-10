package com.projetoIntegradorII.barbearia.security.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoIntegradorII.barbearia.entity.autenticathion.UserAuth;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${auth.jwt-secret}")
    private String secret;

    @Value("${auth.jwt-expiration-miliseg}")
    private int expiration;


    public String generateJwtToken(Authentication authentication) {
        UserAuth userPrincipal = (UserAuth) authentication.getPrincipal();
        return generateJwtToken(userPrincipal.getUsername());
    }

    public String generateJwtToken(JwtToken jwtToken) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Map<String, Object> claims = new HashMap<>(); //create a hashmap

        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> customerData = oMapper.convertValue(jwtToken, Map.class);

        claims.putAll(customerData);

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
            .signWith(key)
            .compact();
    }

    public String generateJwtToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
            .signWith(key)
            .compact();
    }

    //retorna o USER do token jwt com as principais infos disponibilizadas no token
    public UserAuth getUserFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
        UserAuth user = new UserAuth();
        user.setId(Long.parseLong(claims.get("id").toString()));
        user.setEmail(claims.get("email").toString());
        user.setUsername(claims.get("username").toString());
        return user;
    }

    //retorna o username do token jwt
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retorna expiration date do token jwt
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //retorna expiration date do token jwt
    public Long getExpirationSecondsFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration).getTime() / 1000;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //para retornar qualquer informação do token nos iremos precisar da secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String authToken) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {} {}", authToken, e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {} {}", authToken, e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {} {}", authToken, e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {} {}", authToken, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {} {}", authToken, e.getMessage());
        }

        return false;
    }

}
