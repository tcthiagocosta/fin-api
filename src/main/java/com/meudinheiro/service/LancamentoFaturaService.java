package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.LancamentoFatura;
import com.meudinheiro.repository.LancamentoFaturaRepository;

@Service
public class LancamentoFaturaService {
	
	@Autowired
    private LancamentoFaturaRepository lancamentoFaturaRepository;

    public LancamentoFatura salvar(LancamentoFatura lancamentoFatura) {
        return lancamentoFaturaRepository.save(lancamentoFatura);
    }
    
    public Optional<LancamentoFatura> getPorId(Integer id) {
        return lancamentoFaturaRepository.findById(id);
    }

    public List<LancamentoFatura> obterTodos() {
		return lancamentoFaturaRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(LancamentoFatura lancamentoFatura) {
    	lancamentoFaturaRepository.delete(lancamentoFatura);
    }
    
    public LancamentoFatura atualizar(LancamentoFatura lancamentoFatura) {
    	return lancamentoFaturaRepository.save(lancamentoFatura);
    }
}
