package com.devficha.ficharapida.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class SocorristaDTO {
    private Long id;

    private String nome;
    private String matricula;
    private String categoria;
    private String email;
    private String carimbo;
    private String assinatura;
}
