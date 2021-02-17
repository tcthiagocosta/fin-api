package com.meudinheiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.Cartao;
import com.meudinheiro.model.FaturaCartao;
import com.meudinheiro.model.LancamentoFatura;
import com.meudinheiro.repository.LancamentoFaturaRepository;
import com.meudinheiro.util.Funcao;

@Service
public class LancamentoFaturaService {
	
	@Autowired
    private LancamentoFaturaRepository lancamentoFaturaRepository;
	
	@Autowired FaturaCartaoService faturaCartaoService;

    public LancamentoFatura salvar(LancamentoFatura lancamentoFatura, Cartao cartao) {
    	
    	LocalDate dataLancamento = lancamentoFatura.getData();
    	Integer mes = dataLancamento.getMonthValue();
    	Integer ano = dataLancamento.getYear();
    	// pegar a fatura atual da data do lancamento que não esteja paga
    	FaturaCartao faturaCartao = null;
    	
    	faturaCartao = faturaCartaoService.getFaturaPorMesEAnoECartao(mes, ano, cartao);
    	
    	if (faturaCartao == null) {
    		FaturaCartao faturaCartaoNova = new FaturaCartao();
    		faturaCartaoNova.setAno(ano);
    		faturaCartaoNova.setMes(mes);
    		faturaCartaoNova.setCartao(cartao);
    		faturaCartaoNova.setPago(false);
    		faturaCartaoNova.setDescricao("" + cartao.getNome() + " - " + Funcao.getMes(mes) + "/" + ano);
    		
    		faturaCartao = faturaCartaoService.salvar(faturaCartaoNova);
    	}
    	
    	if (!faturaCartao.getPago()) {
    		
    		
    	}
    	
    	// criar um lancamento para cada parcela
    	
    	// pegar a fatura para cada parcela, caso nao esxita criar uma fatura nova e inserir o lancamento
    	
    	
    	
    	
    	
    	
    	
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
