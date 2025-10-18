package com.devficha.ficharapida.business.service;

import com.devficha.ficharapida.business.entities.FichaAtendimento;
import com.devficha.ficharapida.infrastructure.repository.FichaAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaService {
    @Autowired
    private FichaAtendimentoRepository fichaAtendimentoRepository;

    public List<FichaAtendimento> listarTodas(){
        return fichaAtendimentoRepository.findAll();
    }
    public FichaAtendimento salvar(FichaAtendimento fichaAtendimento){
        return fichaAtendimentoRepository.save(fichaAtendimento);
    }
    public void deletar (Long id){
        fichaAtendimentoRepository.deleteById(id);
    }
    public Optional<FichaAtendimento> buscarPorId(Long id){
        return fichaAtendimentoRepository.findById(id);
    }
}
