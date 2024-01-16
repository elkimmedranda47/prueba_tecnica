package com.elkin.pruebatecnica.service.impl;

import com.elkin.pruebatecnica.entities.Usuarios;
import com.elkin.pruebatecnica.repository.IUsuarioRepository;
import com.elkin.pruebatecnica.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    PasswordEncoder passwordEncoder;
    private IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuarios crearUsuario(Usuarios usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuarios actualizarUsuario(Long id, Usuarios usuario) {
        Usuarios usuariosBd = buscarId(id);
        if (usuariosBd != null) {
            usuariosBd.setFirstName(usuario.getFirstName());
            usuario.setEmail(usuario.getEmail());
            usuariosBd.setLastName(usuario.getLastName());
            usuariosBd.setDateOfBirth(usuario.getDateOfBirth());
            usuariosBd.setAddress(usuario.getAddress());
            usuariosBd.setPassword(usuario.getPassword());
            usuariosBd.setMobilePhone(usuario.getMobilePhone());

        }
        return usuarioRepository.save(usuariosBd);
    }

    @Override
    public Usuarios eliminarUsuario(Long id) {
        Usuarios usuariobd = buscarId(id);
        usuarioRepository.deleteById(id);
        return usuariobd;
    }

    @Override
    public List<Usuarios> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuarios buscarId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
