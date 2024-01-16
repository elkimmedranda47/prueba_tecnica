package com.elkin.pruebatecnica.repository;

import com.elkin.pruebatecnica.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByMobilePhone(String phone);
}
