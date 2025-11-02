package com.devficha.ficharapida.business.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Matrícula é obrigatória")
    @Column(nullable = false, unique = true)
    private String matricula;

    @NotBlank(message = "Categoria é obrigatória")
    @Column(nullable = false)
    private String categoria;

    @Email(message = "Email deve ser válido")
    private String email;

    /**
     * Campo gerado automaticamente - não deve ser editado manualmente.
     * Formato: NOME COMPLETO\nCARGO / MATRÍCULA: XXXXX
     */
    @Column(columnDefinition = "TEXT")
    private String carimbo;

    @OneToMany(mappedBy = "socorrista")
    private List<FichaAtendimento> fichaAtendimentos;
}
