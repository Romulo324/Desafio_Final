package br.com.codewave.codewave.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_passageiro")
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    private String cpf;

    @Column(length = 100)
    private String email;

    @Column(length = 25, name = "numero_de_telefone")
    private String numeroDeTelefone;

    @ManyToOne
    private Corrida corrida;

    //TESTANDO...
    private double longitude;

    private double latitude;
}
