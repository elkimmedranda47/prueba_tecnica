package com.elkin.pruebatecnica.security.details;

import com.elkin.pruebatecnica.entities.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsImpl implements UserDetails {
    private String mobilePhone;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Usuarios usuario) {
        List<GrantedAuthority> authorities = Collections.emptyList();
      return new UserDetailsImpl(usuario.getMobilePhone(), usuario.getPassword(),authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return mobilePhone;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
