package br.com.codewave.codewave.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity(name = "tb_passageiro")
public class Passageiro {

    @Id
    @CPF
    private String cpf;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    @Email
    private String email;

    @Column(length = 25, name = "numero_de_telefone")
    private String numeroDeTelefone;

    private double longitude;
    private double latitude;
}
