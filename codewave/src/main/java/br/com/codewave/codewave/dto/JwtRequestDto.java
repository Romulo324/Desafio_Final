package br.com.codewave.codewave.dto;

import lombok.Data;

@Data
public class JwtRequestDto {

    private String username;
    private String password;
}
