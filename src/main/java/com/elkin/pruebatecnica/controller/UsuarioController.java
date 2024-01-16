package com.elkin.pruebatecnica.controller;

import com.elkin.pruebatecnica.entities.Usuarios;
import com.elkin.pruebatecnica.security.dto.JwtDto;
import com.elkin.pruebatecnica.security.dto.LoginUsuarioDTO;
import com.elkin.pruebatecnica.security.jwt.JwtProvider;
import com.elkin.pruebatecnica.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    JwtProvider jwtProvider;
    private IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider, IUsuarioService usuarioService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.usuarioService = usuarioService;
    }


    @PostMapping("/crear")
    public ResponseEntity<Usuarios> crearUsuario(@RequestBody Usuarios usuarios) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuarios));
    }

    @PutMapping("/{id_user}")
    public ResponseEntity<Usuarios> actualizarUsuarios(@PathVariable("id_user") Long idUser, @RequestBody Usuarios usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(idUser, usuario));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @DeleteMapping("/{id_user}")
    public ResponseEntity<Usuarios> eliminarUsuarios(@PathVariable("id_user") Long idUser) {
        return ResponseEntity.ok(usuarioService.eliminarUsuario(idUser));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuarioDTO loginUsuario) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getPhone(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }


}
