package com.meudinheiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.FaturaCartao;
import com.meudinheiro.repository.FaturaCartaoRepository;

@Service
public class FaturaCartaoService {
	
	@Autowired
    private FaturaCartaoRepository faturaCartaoRepository;

    public FaturaCartao salvar(FaturaCartao faturaCartao) {
    	
    	if (faturaCartao.getPago()) {
    		faturaCartao.setDataPagamento(LocalDate.now());
    	}
    	
        return faturaCartaoRepository.save(faturaCartao);
    }
    
    public Optional<FaturaCartao> getPorId(Integer id) {
        return faturaCartaoRepository.findById(id);
    }

    public List<FaturaCartao> obterTodos() {
		return faturaCartaoRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(FaturaCartao faturaCartao) {
    	faturaCartaoRepository.delete(faturaCartao);
    }
    
    public FaturaCartao atualizar(FaturaCartao faturaCartao) {
    	
    	if (faturaCartao.getPago()) {
    		faturaCartao.setDataPagamento(LocalDate.now());
    	}
    	
    	return faturaCartaoRepository.save(faturaCartao);
    }
}
