package br.com.codewave.codewave.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


// Classe de usuario
@Data
@Entity(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    @Email
    private String email;

    @Column(length = 255)
    private String senha;
}
