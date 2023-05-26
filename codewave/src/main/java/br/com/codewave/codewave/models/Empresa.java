package br.com.codewave.codewave.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cnpj;
    @Column(length = 255)
    private String carro;

    @Column(length = 100)
    private String nome;
}
