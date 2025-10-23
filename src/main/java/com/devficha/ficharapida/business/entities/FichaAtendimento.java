package com.devficha.ficharapida.business.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class FichaAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    //Dados do cabecalho
    private LocalDate dataAtendimento;
    private String kmInicial;
    private String kmFinal;

    //Motivo da solicitacao
    private String motivoSolicitacao;

    //Classificacao de risco
    private boolean vermelha;
    private boolean amarela;
    private boolean verde;
    private boolean azul;

    //Dados do local da ocorrencia
    private boolean residencia;
    private boolean viaPublica;
    private boolean rodovia;
    private boolean ps;
    private boolean ubs;
    private String localOcorrenciaOutros;
    private String enderecoOcorrencia;
    private Integer numeroEnderecoOcorrencia;
    private String referenciaEnderecoOcorrencia;
    private String contatoEnderecoOcorrencia;

    //Dados da vitima
    private String nomeVitima;
    private Integer idadeVitima;
    private LocalDate dataNascimentoVitima;
    private String enderecoVitima;
    private String numeroEnderecoVitima;
    private String bairroEnderecoVitima;
    private String nomePaiVitima;
    private String nomeMaeVitima;
    private String cnsVitima;
    private String rgVitima;
    private String cpfVitima;

    //Dados do chamado
    private LocalTime horaChamado;
    private LocalTime horaTransmissao;
    private LocalTime horaSaida;
    private LocalTime horaChegadaLocal;
    private LocalTime horaSaidaLocal;
    private LocalTime horaChegadaHospital;
    private LocalTime horaSaidaHospital;
    private LocalTime horaRetornoBase;

    //Dados do atendimento
        //Tipo de atendimento
        private boolean atropelamento;
        private boolean suspeitaIam;
        private boolean queimaduras;
        private boolean psiquiatrico;
        private boolean transferencia;
        private boolean acidenteTransito;
        private boolean suspeitaAvc;
        private boolean intoxicacao;
        private boolean hipoHipertensao;
        private boolean obstetricia;
        private boolean agressao;
        private boolean queda;
        private boolean hipoHiperglicemia;
        private boolean alcoolizado;
        private String outrosTipoAtendimento;

        //Situacao local
        private boolean morteObvia;
        private boolean chamadoFalso;
        private boolean evadiu;
        private boolean qta;
        private String outrosSituacaoLocal;
        private String descricaoCena;

    //Dados clinicos
        //Avaliacao primaria
        private boolean viaAereaLiberada;
        private boolean respiracao;
        private boolean circulacao;
        private boolean consciente;

        //Pupilas
        private boolean direitaMidriase;
        private boolean direitaIsocoria;
        private boolean direitaMiose;
        private boolean direitaReativa;
        private boolean esquerdaMidriase;
        private boolean esquerdaIsocoria;
        private boolean esquerdaMiose;
        private boolean esquerdaReativa;

        //Sinais vitais
        private Integer pressaoSistolica;
        private Integer pressaoDiastolica;
        private Integer frequenciaCardiaca;
        private Integer frequenciaRespiratoria;
        private Integer saturacao;
        private Integer dextro;
        private Integer temperatura;

        //Pele
        private boolean corada;
        private boolean cianotica;
        private boolean quente;
        private boolean fria;
        private boolean icterica;
        private boolean sudoreica;

        //Antecedentes patologicos
        private boolean iam;
        private boolean avc;
        private boolean diabetes;
        private boolean asmaBronquite;
        private boolean convulsao;
        private boolean has;
        private boolean ca;

        //Medicamentos em uso
        private String medicamentosEmUso;

        //Alergias
        private String alergias;

    //Procedimentos realizados
    private boolean canulaGuedel;
    private boolean oxigenio;
    private boolean oximetria;
    private boolean acessoVenoso;
    private boolean rcp;
    private boolean dea;
    private boolean colarCervical;
    private boolean prancha;
    private boolean ked;
    private boolean talas;
    private boolean contensaoPsq;
    private boolean kitParto;
    private boolean curativo;
    private boolean apoioUsa;
    private String procedimentosRealizadosOutros;

    //Conduta
    private String conduta;

    //Medico regulador
    private String medicoRegulador;

    //Descricao dos pertences do paciente
    private String pertences;

    //Relatorio de enfermagem
    private String relatorioEnfermagem;

    //Campos de isencao de responsabilidade
    private boolean recusaAtendimento;
    private boolean confirmacaoComparecimento;
    private boolean altaLocal;

    //Campos de assinatura e rg
    private String assinaturaResponsavel;
    private String rgResponsavel;

    //Campos de carimbo
    private String carimboDestino;
    private String carimboRecebidoPor;
    private String carimboTecnicoEnfermagem;
    private String carimboSocorrista;

    @ManyToOne
    @JoinColumn(name = "socorrista_id" )
    private Socorrista socorrista;
}
