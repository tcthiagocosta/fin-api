package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.enumeration.Tipo;
import com.meudinheiro.model.Transferencia;
import com.meudinheiro.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
	
	@Autowired
    private TransferenciaRepository transferenciaRepository;
	
	@Autowired
	private LancamentoService lancamentoService;

    public Transferencia salvar(Transferencia transferencia) {
    	transferencia = transferenciaRepository.save(transferencia);
    	
    	// Lançamento conta origem
    	lancamentoService.gerarLancamentoDaTransferencia(transferencia, Tipo.DESPESA);
    	
    	// Lançamento conta destino
    	lancamentoService.gerarLancamentoDaTransferencia(transferencia, Tipo.RECEITA);
    	
    	return transferencia;
    }
    
    public Optional<Transferencia> getPorId(Integer id) {
        return transferenciaRepository.findById(id);
    }

    public List<Transferencia> obterTodos() {
		return transferenciaRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Transferencia transferencia) {
    	transferenciaRepository.delete(transferencia);
    }
    
    public Transferencia atualizar(Transferencia transferencia) {
    	return transferenciaRepository.save(transferencia);
    }
}
