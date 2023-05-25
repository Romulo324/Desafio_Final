package br.com.codewave.codewave.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class JwtRequestDto implements Serializable {
    private static final long serialVersionUID = 1l;

    private String username;
    private String password;
}
