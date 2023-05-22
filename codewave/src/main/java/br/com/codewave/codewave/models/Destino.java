package br.com.codewave.codewave.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_destino")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String local;

    @Column(length = 255, name = "ponto_de_partida")
    private String pontoDePartido;

    @Column(length = 255, name = "ponto_de_chegada")
    private String pontoDeChegada;
}
