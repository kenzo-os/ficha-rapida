package com.devficha.ficharapida.business.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Socorrista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;
    private String categoria;
    private String email;
    private String carimbo;
    private String assinatura;
    @OneToMany(mappedBy = "socorrista")
    private List<FichaAtendimento> fichaAtendimentos;
}
