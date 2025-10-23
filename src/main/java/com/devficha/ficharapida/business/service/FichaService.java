package com.devficha.ficharapida.business.service;

import com.devficha.ficharapida.business.entities.FichaAtendimento;
import com.devficha.ficharapida.infrastructure.repository.FichaAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FichaService {

    @Autowired
    private FichaAtendimentoRepository fichaRepository;

    @Transactional
    public FichaAtendimento salvar(FichaAtendimento ficha) {
        return fichaRepository.save(ficha);
    }

    public FichaAtendimento buscarPorId(Long id) {
        return fichaRepository.findById(id).orElse(null);
    }

    public List<FichaAtendimento> listarTodas() {
        return fichaRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        fichaRepository.deleteById(id);
    }

    @Transactional
    public FichaAtendimento atualizar(Long id, FichaAtendimento fichaAtualizada) {
        FichaAtendimento fichaExistente = buscarPorId(id);
        if (fichaExistente != null) {
            fichaAtualizada.setId(id);
            return fichaRepository.save(fichaAtualizada);
        }
        return null;
    }
}
