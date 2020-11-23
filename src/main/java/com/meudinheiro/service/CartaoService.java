package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.Cartao;
import com.meudinheiro.repository.CartaoRepository;

@Service
public class CartaoService {
	
	@Autowired
    private CartaoRepository cartaoRepository;

    public Cartao salvar(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }
    
    public Optional<Cartao> getPorId(Integer id) {
        return cartaoRepository.findById(id);
    }

    public List<Cartao> obterTodos() {
		return cartaoRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Cartao cartao) {
    	cartaoRepository.delete(cartao);
    }
    
    public Cartao atualizar(Cartao cartao) {
    	return cartaoRepository.save(cartao);
    }
}
