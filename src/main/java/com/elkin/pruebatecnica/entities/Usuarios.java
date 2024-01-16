package com.elkin.pruebatecnica.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    private String dateOfBirth;
    private String address;
    @Column(nullable = false)
    private String token;
    @Column(length = 120)
    private String password;
    @Column(length = 50)
    private String mobilePhone;
    @Column(length = 50)
    private String email;
}
