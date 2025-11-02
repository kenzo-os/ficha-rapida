package com.devficha.ficharapida.business.service;

import com.devficha.ficharapida.business.entities.Socorrista;
import com.devficha.ficharapida.infrastructure.repository.SocorristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocorristaService {

    @Autowired
    private SocorristaRepository socorristaRepository;

    @Transactional
    public Socorrista salvar(Socorrista socorrista) {
        gerarCarimbo(socorrista);
        return socorristaRepository.save(socorrista);
    }

    public Socorrista buscarPorId(Long id) {
        return socorristaRepository.findById(id).orElse(null);
    }

    public List<Socorrista> listarTodos() {
        return socorristaRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        socorristaRepository.deleteById(id);
    }

    @Transactional
    public Socorrista atualizar(Long id, Socorrista socorristaAtualizado) {
        Socorrista socorristaExistente = buscarPorId(id);
        if (socorristaExistente != null) {
            socorristaAtualizado.setId(id);
            gerarCarimbo(socorristaAtualizado);
            return socorristaRepository.save(socorristaAtualizado);
        }
        return null;
    }

    private void gerarCarimbo(Socorrista socorrista) {
        String nome = socorrista.getNome() != null ? socorrista.getNome() : "";
        String categoria = socorrista.getCategoria() != null ? socorrista.getCategoria() : "";
        String matricula = socorrista.getMatricula() != null ? socorrista.getMatricula() : "";

        if (nome.isEmpty() || categoria.isEmpty() || matricula.isEmpty()) {
            socorrista.setCarimbo(null);
            return;
        }

        socorrista.setCarimbo(nome + "\n" + categoria + " / " + matricula);
    }
}