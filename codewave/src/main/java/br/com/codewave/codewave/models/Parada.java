package br.com.codewave.codewave.Models;

import jakarta.persistence.*;
import lombok.Data;

// Classe parada
@Data
@Entity(name = "tb_parada")
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String localDaParada;
    @ManyToOne
    private Corrida corrida;
}
