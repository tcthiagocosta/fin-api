package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.Movimento;
import com.meudinheiro.repository.MovimentoRepository;

@Service
public class MovimentoService {
	
	@Autowired
    private MovimentoRepository movimentoRepository;

    public Movimento salvar(Movimento movimento) {
        return movimentoRepository.save(movimento);
    }
    
    public Optional<Movimento> getPorId(Integer id) {
        return movimentoRepository.findById(id);
    }

    public List<Movimento> obterTodos() {
		return movimentoRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Movimento movimento) {
    	movimentoRepository.delete(movimento);
    }
    
    public Movimento atualizar(Movimento movimento) {
    	return movimentoRepository.save(movimento);
    }
}
