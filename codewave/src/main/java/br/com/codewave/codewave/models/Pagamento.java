package br.com.codewave.codewave.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

// Classe Pagamento
@Data
@Entity(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorMinimo;

    @Column(precision =10, scale = 2)
    private BigDecimal valorFinal;

    private double porcentagemDoMotorista;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorBase;

    @Column(precision = 10, scale = 2)
    private BigDecimal resultadoPorcentagemDoMotorista;

}
