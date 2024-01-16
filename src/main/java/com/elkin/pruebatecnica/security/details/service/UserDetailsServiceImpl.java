package com.elkin.pruebatecnica.security.details.service;

import com.elkin.pruebatecnica.entities.Usuarios;
import com.elkin.pruebatecnica.repository.IUsuarioRepository;
import com.elkin.pruebatecnica.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    IUsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailsServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Usuarios usuario = usuarioRepository.findByMobilePhone(phone).get();
        return UserDetailsImpl.build(usuario);
    }
}
