package com.devficha.ficharapida.infrastructure.repository;

import com.devficha.ficharapida.business.entities.FichaAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FichaAtendimentoRepository extends JpaRepository<FichaAtendimento, Long> {

    List<FichaAtendimento> findBySocorristaId(Long socorristaId);

    // Corrigido: o campo na entidade é 'dataAtendimento', não 'dataFicha'
    List<FichaAtendimento> findByDataAtendimento(LocalDate data);

}
