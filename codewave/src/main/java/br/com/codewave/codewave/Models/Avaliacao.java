package br.com.codewave.codewave.Models;

import br.com.codewave.codewave.Models.enums.AvaliacaoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


// Classe de avaliação
@Data
@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private AvaliacaoEnum avaliacao;

}
