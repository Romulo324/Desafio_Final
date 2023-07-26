package br.com.codewave.codewave.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity(name = "tb_passageiro_internacional")
public class PassageiroInternacional {

    @Id
    @CPF
    private String cpf;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    @Email
    private String email;

    private String numeroPassaporte;

    @Column(length = 9, name = "numero_de_telefone")
    private String numeroDeTelefone;

    private double longitude;
    private double latitude;

    private String senha;
}