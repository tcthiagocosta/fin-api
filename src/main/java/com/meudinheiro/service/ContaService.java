package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.Conta;
import com.meudinheiro.repository.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
    private ContaRepository contaRepository;

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }
    
    public Optional<Conta> getPorId(Integer id) {
        return contaRepository.findById(id);
    }

    public List<Conta> obterTodos() {
		return contaRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Conta conta) {
    	contaRepository.delete(conta);
    }
    
    public Conta atualizar(Conta conta) {
    	return contaRepository.save(conta);
    }
}
