package com.elkin.pruebatecnica.service;

import com.elkin.pruebatecnica.entities.Usuarios;

import java.util.List;

public interface IUsuarioService {
    public Usuarios crearUsuario(Usuarios usuario);
    public Usuarios actualizarUsuario(Long id ,Usuarios usuario);
    public Usuarios eliminarUsuario(Long id);
    public List<Usuarios> listarUsuarios();
    public Usuarios buscarId(Long id);

}
