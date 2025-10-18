package com.devficha.ficharapida.business.mapper;

import com.devficha.ficharapida.business.dto.FichaDTO;
import com.devficha.ficharapida.business.entities.FichaAtendimento;
import com.devficha.ficharapida.business.entities.Socorrista;

public class FichaMapper {

    public static FichaDTO toDTO(FichaAtendimento entity){
        FichaDTO dto = new FichaDTO();
        dto.setId(entity.getId());
        dto.setDataAtendimento(entity.getDataAtendimento());
        dto.setMotivoSolicitacao(entity.getMotivoSolicitacao());
        dto.setClassificacaoRisco(entity.getClassificacaoRisco());
        dto.setNomeVitima(entity.getNomeVitima());
        dto.setConduta(entity.getConduta());
        dto.setIdade(entity.getIdade());
        dto.setEndereco(entity.getEndereco());
        dto.setAlergias(entity.getAlergias());
        dto.setDescricaoCena(entity.getDescricaoCena());
        dto.setMedicamentosEmUso(entity.getMedicamentosEmUso());
        dto.setHas(entity.isHas());
        dto.setIam(entity.isIam());
        dto.setCa(entity.isCa());
        dto.setAvc(entity.isAvc());
        dto.setAsma(entity.isAsma());
        dto.setDiabetes(entity.isDiabetes());
        dto.setConvulsao(entity.isConvulsao());

        return dto;
    }

    public static FichaAtendimento toEntity(FichaDTO dto, Socorrista socorrista){
        FichaAtendimento entity = new FichaAtendimento();
        entity.setId(dto.getId());
        entity.setDataAtendimento(dto.getDataAtendimento());
        entity.setMotivoSolicitacao(dto.getMotivoSolicitacao());
        entity.setClassificacaoRisco(dto.getClassificacaoRisco());
        entity.setNomeVitima(dto.getNomeVitima());
        entity.setConduta(dto.getConduta());
        entity.setIdade(dto.getIdade());
        entity.setEndereco(dto.getEndereco());
        entity.setAlergias(dto.getAlergias());
        entity.setDescricaoCena(dto.getDescricaoCena());
        entity.setMedicamentosEmUso(dto.getMedicamentosEmUso());
        entity.setHas(dto.isHas());
        entity.setIam(dto.isIam());
        entity.setCa(dto.isCa());
        entity.setAvc(dto.isAvc());
        entity.setAsma(dto.isAsma());
        entity.setDiabetes(dto.isDiabetes());
        entity.setConvulsao(dto.isConvulsao());
        return entity;
    }
}
