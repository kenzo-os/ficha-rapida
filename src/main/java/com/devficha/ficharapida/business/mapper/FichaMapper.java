package com.devficha.ficharapida.business.mapper;

import com.devficha.ficharapida.business.dto.FichaDTO;
import com.devficha.ficharapida.business.entities.FichaAtendimento;
import com.devficha.ficharapida.business.entities.Socorrista;

public class FichaMapper {

    public static FichaDTO toDTO(FichaAtendimento entity){
        FichaDTO dto = new FichaDTO();
        dto.setId(entity.getId());

        //Dados do cabecalho
        dto.setDataAtendimento(entity.getDataAtendimento());
        dto.setKmInicial(entity.getKmInicial());
        dto.setKmFinal(entity.getKmFinal());

        //Motivo da solicitacao
        dto.setMotivoSolicitacao(entity.getMotivoSolicitacao());

        //Classificacao de risco
        dto.setVermelha(entity.isVermelha());
        dto.setAmarela(entity.isAmarela());
        dto.setVerde(entity.isVerde());
        dto.setAzul(entity.isAzul());

        //Dados do local da ocorrencia
        dto.setResidencia(entity.isResidencia());
        dto.setViaPublica(entity.isViaPublica());
        dto.setRodovia(entity.isRodovia());
        dto.setPs(entity.isPs());
        dto.setUbs(entity.isUbs());
        dto.setLocalOcorrenciaOutros(entity.getLocalOcorrenciaOutros());
        dto.setEnderecoOcorrencia(entity.getEnderecoOcorrencia());
        dto.setNumeroEnderecoOcorrencia(entity.getNumeroEnderecoOcorrencia());
        dto.setReferenciaEnderecoOcorrencia(entity.getReferenciaEnderecoOcorrencia());
        dto.setContatoEnderecoOcorrencia(entity.getContatoEnderecoOcorrencia());

        //Dados da vitima
        dto.setNomeVitima(entity.getNomeVitima());
        dto.setIdadeVitima(entity.getIdadeVitima());
        dto.setDataNascimentoVitima(entity.getDataNascimentoVitima());
        dto.setEnderecoVitima(entity.getEnderecoVitima());
        dto.setNumeroEnderecoVitima(entity.getNumeroEnderecoVitima());
        dto.setBairroEnderecoVitima(entity.getBairroEnderecoVitima());
        dto.setNomePaiVitima(entity.getNomePaiVitima());
        dto.setNomeMaeVitima(entity.getNomeMaeVitima());
        dto.setCnsVitima(entity.getCnsVitima());
        dto.setRgVitima(entity.getRgVitima());
        dto.setCpfVitima(entity.getCpfVitima());

        //Dados do chamado
        dto.setHoraChamado(entity.getHoraChamado());
        dto.setHoraTransmissao(entity.getHoraTransmissao());
        dto.setHoraSaida(entity.getHoraSaida());
        dto.setHoraChegadaLocal(entity.getHoraChegadaLocal());
        dto.setHoraSaidaLocal(entity.getHoraSaidaLocal());
        dto.setHoraChegadaHospital(entity.getHoraChegadaHospital());
        dto.setHoraSaidaHospital(entity.getHoraSaidaHospital());
        dto.setHoraRetornoBase(entity.getHoraRetornoBase());

        //Dados do atendimento
            //Tipo de atendimento
            dto.setAtropelamento(entity.isAtropelamento());
            dto.setSuspeitaIam(entity.isSuspeitaIam());
            dto.setQueimaduras(entity.isQueimaduras());
            dto.setPsiquiatrico(entity.isPsiquiatrico());
            dto.setTransferencia(entity.isTransferencia());
            dto.setAcidenteTransito(entity.isAcidenteTransito());
            dto.setSuspeitaAvc(entity.isSuspeitaAvc());
            dto.setIntoxicacao(entity.isIntoxicacao());
            dto.setHipoHipertensao(entity.isHipoHipertensao());
            dto.setObstetricia(entity.isObstetricia());
            dto.setAgressao(entity.isAgressao());
            dto.setQueda(entity.isQueda());
            dto.setHipoHiperglicemia(entity.isHipoHiperglicemia());
            dto.setAlcoolizado(entity.isAlcoolizado());
            dto.setOutrosTipoAtendimento(entity.getOutrosTipoAtendimento());

            //Situacao local
             dto.setMorteObvia(entity.isMorteObvia());
             dto.setChamadoFalso(entity.isChamadoFalso());
             dto.setEvadiu(entity.isEvadiu());
             dto.setQta(entity.isQta());
             dto.setOutrosSituacaoLocal(entity.getOutrosSituacaoLocal());
             dto.setDescricaoCena(entity.getDescricaoCena());

        //Dados clinicos
            //Avaliacao primaria
            dto.setViaAereaLiberada(entity.isViaAereaLiberada());
            dto.setRespiracao(entity.isRespiracao());
            dto.setCirculacao(entity.isCirculacao());
            dto.setConsciente(entity.isConsciente());

            //Pupilas
            dto.setDireitaMidriase(entity.isDireitaMidriase());
            dto.setDireitaIsocoria(entity.isDireitaIsocoria());
            dto.setDireitaMiose(entity.isDireitaMiose());
            dto.setDireitaReativa(entity.isDireitaReativa());
            dto.setEsquerdaMidriase(entity.isEsquerdaMidriase());
            dto.setEsquerdaIsocoria(entity.isEsquerdaIsocoria());
            dto.setEsquerdaMiose(entity.isEsquerdaMiose());
            dto.setEsquerdaReativa(entity.isEsquerdaReativa());

            //Sinais vitais
            dto.setPressaoSistolica(entity.getPressaoSistolica());
            dto.setPressaoDiastolica(entity.getPressaoDiastolica());
            dto.setFrequenciaCardiaca(entity.getFrequenciaCardiaca());
            dto.setFrequenciaRespiratoria(entity.getFrequenciaRespiratoria());
            dto.setSaturacao(entity.getSaturacao());
            dto.setDextro(entity.getDextro());
            dto.setTemperatura(entity.getTemperatura());

            //Pele
            dto.setCorada(entity.isCorada());
            dto.setCianotica(entity.isCianotica());
            dto.setQuente(entity.isQuente());
            dto.setFria(entity.isFria());
            dto.setIcterica(entity.isIcterica());
            dto.setSudoreica(entity.isSudoreica());

            //Antecedentes patologicos
            dto.setIam(entity.isIam());
            dto.setAvc(entity.isAvc());
            dto.setDiabetes(entity.isDiabetes());
            dto.setAsmaBronquite(entity.isAsmaBronquite());
            dto.setConvulsao(entity.isConvulsao());
            dto.setHas(entity.isHas());
            dto.setCa(entity.isCa());

            //Medicamentos em uso
            dto.setMedicamentosEmUso(entity.getMedicamentosEmUso());

            //Alergias
            dto.setAlergias(entity.getAlergias());

    //Procedimentos realizados
        dto.setCanulaGuedel(entity.isCanulaGuedel());
        dto.setOxigenio(entity.isOxigenio());
        dto.setOximetria(entity.isOximetria());
        dto.setAcessoVenoso(entity.isAcessoVenoso());
        dto.setRcp(entity.isRcp());
        dto.setDea(entity.isDea());
        dto.setColarCervical(entity.isColarCervical());
        dto.setPrancha(entity.isPrancha());
        dto.setKed(entity.isKed());
        dto.setTalas(entity.isTalas());
        dto.setContensaoPsq(entity.isContensaoPsq());
        dto.setKitParto(entity.isKitParto());
        dto.setCurativo(entity.isCurativo());
        dto.setApoioUsa(entity.isApoioUsa());
        dto.setProcedimentosRealizadosOutros(entity.getProcedimentosRealizadosOutros());

        //Conduta
        dto.setConduta(entity.getConduta());

        //Medico regulador
        dto.setMedicoRegulador(entity.getMedicoRegulador());

        //Descricao dos pertences do paciente
        dto.setPertences(entity.getPertences());

        //Relatorio de enfermagem
        dto.setRelatorioEnfermagem(entity.getRelatorioEnfermagem());

        //Campos de isencao de responsabilidade
        dto.setRecusaAtendimento(entity.isRecusaAtendimento());
        dto.setConfirmacaoComparecimento(entity.isConfirmacaoComparecimento());
        dto.setAltaLocal(entity.isAltaLocal());

        //Campos de assinatura e rg
        dto.setAssinaturaResponsavel(entity.getAssinaturaResponsavel());
        dto.setRgResponsavel(entity.getRgResponsavel());

        //Campos de carimbo
        dto.setCarimboDestino(entity.getCarimboDestino());
        dto.setCarimboRecebidoPor(entity.getCarimboRecebidoPor());
        dto.setCarimboTecnicoEnfermagem(entity.getCarimboTecnicoEnfermagem());
        dto.setCarimboSocorrista(entity.getCarimboSocorrista());

        return dto;
    }

    public static FichaAtendimento toEntity(FichaDTO dto, Socorrista socorrista){
        FichaAtendimento entity = new FichaAtendimento();
        entity.setId(dto.getId());

        //Dados do cabecalho
        entity.setDataAtendimento(dto.getDataAtendimento());
        entity.setKmInicial(dto.getKmInicial());
        entity.setKmFinal(dto.getKmFinal());

        //Motivo da solicitacao
        entity.setMotivoSolicitacao(dto.getMotivoSolicitacao());

        //Classificacao de risco
        entity.setVermelha(dto.isVermelha());
        entity.setAmarela(dto.isAmarela());
        entity.setVerde(dto.isVerde());
        entity.setAzul(dto.isAzul());

        //Dados do local da ocorrencia
        entity.setResidencia(dto.isResidencia());
        entity.setViaPublica(dto.isViaPublica());
        entity.setRodovia(dto.isRodovia());
        entity.setPs(dto.isPs());
        entity.setUbs(dto.isUbs());
        entity.setLocalOcorrenciaOutros(dto.getLocalOcorrenciaOutros());
        entity.setEnderecoOcorrencia(dto.getEnderecoOcorrencia());
        entity.setNumeroEnderecoOcorrencia(dto.getNumeroEnderecoOcorrencia());
        entity.setReferenciaEnderecoOcorrencia(dto.getReferenciaEnderecoOcorrencia());
        entity.setContatoEnderecoOcorrencia(dto.getContatoEnderecoOcorrencia());

        //Dados da vitima
        entity.setNomeVitima(dto.getNomeVitima());
        entity.setIdadeVitima(dto.getIdadeVitima());
        entity.setDataNascimentoVitima(dto.getDataNascimentoVitima());
        entity.setEnderecoVitima(dto.getEnderecoVitima());
        entity.setNumeroEnderecoVitima(dto.getNumeroEnderecoVitima());
        entity.setBairroEnderecoVitima(dto.getBairroEnderecoVitima());
        entity.setNomePaiVitima(dto.getNomePaiVitima());
        entity.setNomeMaeVitima(dto.getNomeMaeVitima());
        entity.setCnsVitima(dto.getCnsVitima());
        entity.setRgVitima(dto.getRgVitima());
        entity.setCpfVitima(dto.getCpfVitima());

        //Dados do chamado
        entity.setHoraChamado(dto.getHoraChamado());
        entity.setHoraTransmissao(dto.getHoraTransmissao());
        entity.setHoraSaida(dto.getHoraSaida());
        entity.setHoraChegadaLocal(dto.getHoraChegadaLocal());
        entity.setHoraSaidaLocal(dto.getHoraSaidaLocal());
        entity.setHoraChegadaHospital(dto.getHoraChegadaHospital());
        entity.setHoraSaidaHospital(dto.getHoraSaidaHospital());
        entity.setHoraRetornoBase(dto.getHoraRetornoBase());

        //Dados do atendimento
            //Tipo de atendimento
            entity.setAtropelamento(dto.isAtropelamento());
            entity.setSuspeitaIam(dto.isSuspeitaIam());
            entity.setQueimaduras(dto.isQueimaduras());
            entity.setPsiquiatrico(dto.isPsiquiatrico());
            entity.setTransferencia(dto.isTransferencia());
            entity.setAcidenteTransito(dto.isAcidenteTransito());
            entity.setSuspeitaAvc(dto.isSuspeitaAvc());
            entity.setIntoxicacao(dto.isIntoxicacao());
            entity.setHipoHipertensao(dto.isHipoHipertensao());
            entity.setObstetricia(dto.isObstetricia());
            entity.setAgressao(dto.isAgressao());
            entity.setQueda(dto.isQueda());
            entity.setHipoHiperglicemia(dto.isHipoHiperglicemia());
            entity.setAlcoolizado(dto.isAlcoolizado());
            entity.setOutrosTipoAtendimento(dto.getOutrosTipoAtendimento());

            //Situacao local
            entity.setMorteObvia(dto.isMorteObvia());
            entity.setChamadoFalso(dto.isChamadoFalso());
            entity.setEvadiu(dto.isEvadiu());
            entity.setQta(dto.isQta());
            entity.setOutrosSituacaoLocal(dto.getOutrosSituacaoLocal());
            entity.setDescricaoCena(dto.getDescricaoCena());

        //Dados clinicos
            //Avaliacao primaria
            entity.setViaAereaLiberada(dto.isViaAereaLiberada());
            entity.setRespiracao(dto.isRespiracao());
            entity.setCirculacao(dto.isCirculacao());
            entity.setConsciente(dto.isConsciente());

            //Pupilas
            entity.setDireitaMidriase(dto.isDireitaMidriase());
            entity.setDireitaIsocoria(dto.isDireitaIsocoria());
            entity.setDireitaMiose(dto.isDireitaMiose());
            entity.setDireitaReativa(dto.isDireitaReativa());
            entity.setEsquerdaMidriase(dto.isEsquerdaMidriase());
            entity.setEsquerdaIsocoria(dto.isEsquerdaIsocoria());
            entity.setEsquerdaMiose(dto.isEsquerdaMiose());
            entity.setEsquerdaReativa(dto.isEsquerdaReativa());

            //Sinais vitais
            entity.setPressaoSistolica(dto.getPressaoSistolica());
            entity.setPressaoDiastolica(dto.getPressaoDiastolica());
            entity.setFrequenciaCardiaca(dto.getFrequenciaCardiaca());
            entity.setFrequenciaRespiratoria(dto.getFrequenciaRespiratoria());
            entity.setSaturacao(dto.getSaturacao());
            entity.setDextro(dto.getDextro());
            entity.setTemperatura(dto.getTemperatura());

            //Pele
            entity.setCorada(dto.isCorada());
            entity.setCianotica(dto.isCianotica());
            entity.setQuente(dto.isQuente());
            entity.setFria(dto.isFria());
            entity.setIcterica(dto.isIcterica());
            entity.setSudoreica(dto.isSudoreica());

            //Antecedentes patologicos
            entity.setIam(dto.isIam());
            entity.setAvc(dto.isAvc());
            entity.setDiabetes(dto.isDiabetes());
            entity.setAsmaBronquite(dto.isAsmaBronquite());
            entity.setConvulsao(dto.isConvulsao());
            entity.setHas(dto.isHas());
            entity.setCa(dto.isCa());

            //Medicamentos em uso
            entity.setMedicamentosEmUso(dto.getMedicamentosEmUso());

            //Alergias
            entity.setAlergias(dto.getAlergias());

        //Procedimentos realizados
        entity.setCanulaGuedel(dto.isCanulaGuedel());
        entity.setOxigenio(dto.isOxigenio());
        entity.setOximetria(dto.isOximetria());
        entity.setAcessoVenoso(dto.isAcessoVenoso());
        entity.setRcp(dto.isRcp());
        entity.setDea(dto.isDea());
        entity.setColarCervical(dto.isColarCervical());
        entity.setPrancha(dto.isPrancha());
        entity.setKed(dto.isKed());
        entity.setTalas(dto.isTalas());
        entity.setContensaoPsq(dto.isContensaoPsq());
        entity.setKitParto(dto.isKitParto());
        entity.setCurativo(dto.isCurativo());
        entity.setApoioUsa(dto.isApoioUsa());
        entity.setProcedimentosRealizadosOutros(dto.getProcedimentosRealizadosOutros());

        //Conduta
        entity.setConduta(dto.getConduta());

        //Medico regulador
        entity.setMedicoRegulador(dto.getMedicoRegulador());

        //Descricao dos pertences do paciente
        entity.setPertences(dto.getPertences());

        //Relatorio de enfermagem
        entity.setRelatorioEnfermagem(dto.getRelatorioEnfermagem());

        //Campos de isencao de responsabilidade
        entity.setRecusaAtendimento(dto.isRecusaAtendimento());
        entity.setConfirmacaoComparecimento(dto.isConfirmacaoComparecimento());
        entity.setAltaLocal(dto.isAltaLocal());

        //Campos de assinatura e rg
        entity.setAssinaturaResponsavel(dto.getAssinaturaResponsavel());
        entity.setRgResponsavel(dto.getRgResponsavel());

        //Campos de carimbo
        entity.setCarimboDestino(dto.getCarimboDestino());
        entity.setCarimboRecebidoPor(dto.getCarimboRecebidoPor());
        entity.setCarimboTecnicoEnfermagem(dto.getCarimboTecnicoEnfermagem());
        entity.setCarimboSocorrista(dto.getCarimboSocorrista());

        return entity;
    }
}
