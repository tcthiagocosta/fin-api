package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.Lancamento;
import com.meudinheiro.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }
    
    public Optional<Lancamento> getPorId(Integer id) {
        return lancamentoRepository.findById(id);
    }

    public List<Lancamento> obterTodos() {
		return lancamentoRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Lancamento lancamento) {
    	lancamentoRepository.delete(lancamento);
    }
    
    public Lancamento atualizar(Lancamento lancamento) {
    	return lancamentoRepository.save(lancamento);
    }
}
