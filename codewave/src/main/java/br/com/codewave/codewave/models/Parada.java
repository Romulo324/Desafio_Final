package br.com.codewave.codewave.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "tb_parada")
public class Parada {

    @Id
    private Integer id;
    private String localDaParada;
    @ManyToOne
    private Corrida corrida;
}
