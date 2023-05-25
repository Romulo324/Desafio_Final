package br.com.codewave.codewave.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Entity(name = "tb_empresa")
public class Empresa {

    @Id
    @CNPJ
    private String cnpj;
    @Column(length = 255)
    private String carro;

    @Column(length = 100)
    private String nome;
}
