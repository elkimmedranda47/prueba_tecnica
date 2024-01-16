package com.elkin.pruebatecnica.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String usuario;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String usuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.usuario = usuario;
        this.authorities = authorities;
    }
}