package com.devficha.ficharapida.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class FichaDTO {
    private Long id;
    //Dados brutos ficha
    private LocalDate dataAtendimento;
    private String tipoAtendimento;
    private String classificacaoRisco;
    private String descricaoCena;
    private String motivoSolicitacao;
    private String localOcorrencia;
    private String conduta;

    //Dados da vitima
    private String nomeVitima;
    private Integer idade;
    private String cpf;
    private String endereco;
    private String alergias;
    private String medicamentosEmUso;

    //Antecedentes patológicos
    private boolean has;
    private boolean iam;
    private boolean avc;
    private boolean asma;
    private boolean diabetes;
    private boolean convulsao;
    private boolean ca;
}
