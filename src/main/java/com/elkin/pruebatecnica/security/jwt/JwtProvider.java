package com.elkin.pruebatecnica.security.jwt;

import com.elkin.pruebatecnica.security.details.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${security.jwt.token.secret-key}")
    private String secret;

    @Value("${security.jwt.token.expire-length}")
    private int expiration;

    public String generateToken(Authentication authentication) {

        UserDetailsImpl usuarioLogueado = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(usuarioLogueado.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 180))
                .signWith(getSecret(secret))
                .compact();
    }


    public String getPhoneUsuarioFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token " + token);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("token " + token);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("token " + token);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Token " + token);
        } catch (SignatureException e) {
            throw new RuntimeException("token " + token);
        }
    }

    private Key getSecret(String secret) {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
