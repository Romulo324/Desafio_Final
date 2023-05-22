package br.com.codewave.codewave.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String senha;
}
