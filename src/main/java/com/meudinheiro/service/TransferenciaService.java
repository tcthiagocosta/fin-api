package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.enumeration.Tipo;
import com.meudinheiro.model.Lancamento;
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
    	Lancamento lancOrigem = lancamentoService.gerarLancamentoDaTransferencia(transferencia, Tipo.DESPESA);
    	lancamentoService.salvar(lancOrigem);
    	
    	// Lançamento conta destino
    	Lancamento lancDestino = lancamentoService.gerarLancamentoDaTransferencia(transferencia, Tipo.RECEITA);
    	lancamentoService.salvar(lancDestino);
    	
    	return transferencia;
    }
    
    public Optional<Transferencia> getPorId(Integer id) {
        return transferenciaRepository.findById(id);
    }

    public List<Transferencia> obterTodos() {
		return transferenciaRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Transferencia transferencia) {
    	
    	// removendo os lançamentos vinculados
    	lancamentoService.removeLancamentoTransferencia(transferencia.getId());
    	
    	transferenciaRepository.delete(transferencia);
    }
    
    public Transferencia atualizar(Transferencia transferencia) {
    	
    	// Lançamento conta origem
    	Lancamento lancOrigemNovo = lancamentoService.gerarLancamentoDaTransferencia(transferencia, Tipo.DESPESA);
    	Lancamento lancOrigemSalvo = lancamentoService.getPorTransferenciaETipo(transferencia.getId(), Tipo.DESPESA.toString());
    	lancOrigemNovo.setId(lancOrigemSalvo.getId());
    	lancamentoService.salvar(lancOrigemNovo);
    	
    	// Lançamento conta destino
    	Lancamento lancDestinoNovo = lancamentoService.gerarLancamentoDaTransferencia(transferencia, Tipo.RECEITA);
    	Lancamento lancDestinoSalvo = lancamentoService.getPorTransferenciaETipo(transferencia.getId(), Tipo.RECEITA.toString());
    	lancDestinoNovo.setId(lancDestinoSalvo.getId());
    	lancamentoService.salvar(lancDestinoNovo);
    	
    	transferenciaRepository.save(transferencia);
    	return transferencia; 
    }
}
