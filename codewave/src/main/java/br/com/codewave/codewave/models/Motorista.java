package br.com.codewave.codewave.Models;

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

    //TESTANDO...
    private double longitude;

    private double latitude;

    @Column(nullable = true, length = 64)
    private String photo;

    @Transient
    public String getPhotosImagePath(){
        if (photo == null || cpf == null) return null;

        return "motorista-photo/" + cpf + "/" + photo;
    }
}
