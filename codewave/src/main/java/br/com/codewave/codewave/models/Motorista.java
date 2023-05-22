package br.com.codewave.codewave.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_motorista")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cpf;

    private String carro;

    @Column(name = "nome_completo", length = 100)
    private String nomeCompleto;

    @Column(name = "nome_da_habilitacao", length = 50)
    private String numeroDaHabilitacao;

    @ManyToOne
    private Empresa empresa;

}
