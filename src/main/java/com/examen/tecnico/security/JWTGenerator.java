package com.examen.tecnico.security;

import com.examen.tecnico.controller.ProductController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.examen.tecnico.security.SecurityConstans.JWT_EXPIRATION;
import static com.examen.tecnico.security.SecurityConstans.JWT_SECRET;
@Component
public class JWTGenerator {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    public  String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return token;
    }

    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJwt(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch (IllegalArgumentException e){
            LOGGER.error("Unable to get JWT Token", e);
            throw new RuntimeException(e);
        }catch (ExpiredJwtException e){
            LOGGER.error("JWT Token has expired", e);
            throw new RuntimeException(e);
        }
    }
}
