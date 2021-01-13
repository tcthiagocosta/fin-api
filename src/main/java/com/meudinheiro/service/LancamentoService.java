package com.meudinheiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.dto.LancamentoDTO;
import com.meudinheiro.enumeration.Tipo;
import com.meudinheiro.model.Conta;
import com.meudinheiro.model.Lancamento;
import com.meudinheiro.model.Transferencia;
import com.meudinheiro.repository.LancamentoRepository;
import com.meudinheiro.util.Funcao;

@Service
public class LancamentoService {
	
	@Autowired
    private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private SubCategoriaService subCategoriaService;

    public Lancamento salvar(Lancamento lancamento) {
    	if (lancamento.getPago()) {
    		lancamento.setDataPagamento(LocalDate.now());
    	}
    	
        return lancamentoRepository.save(lancamento);
    }
    
    public Optional<LancamentoDTO> getPorId(Integer id) {
    	
    	Lancamento lancamento = lancamentoRepository.findById(id).orElse(null);
    	LancamentoDTO dto = builderLancamentoDTO(lancamento);
		return Optional.ofNullable(dto);
    }

    public List<Lancamento> obterTodos() {
		return lancamentoRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Integer id) {
    	lancamentoRepository.deleteById(id);
    }
    
    public LancamentoDTO atualizar(LancamentoDTO lancamentoDTO) {
    	
    	Lancamento lanc = lancamentoRepository.save(builderLancamento(lancamentoDTO));
    	
    	return builderLancamentoDTO(lanc);
    }
    
    private LancamentoDTO builderLancamentoDTO(Lancamento lancamento) {
    	LancamentoDTO dto = new LancamentoDTO();
    	dto.setId(lancamento.getId());
    	dto.setConta(lancamento.getConta());
    	dto.setCategoria(lancamento.getSubCategoria().getCategoria());
    	dto.setSubCategoria(lancamento.getSubCategoria());
    	
    	try {
    		dto.setFaturaCartao(lancamento.getFaturaCartao());
		} catch (Exception e) {
			dto.setFaturaCartao(null);
		}
    	
    	dto.setDescricao(lancamento.getDescricao());
    	dto.setTipo(lancamento.getTipo());
    	dto.setValor(lancamento.getValor());
    	dto.setData(Funcao.localDateToString(lancamento.getData()));
    	dto.setPago(lancamento.getPago() ? "SIM" : "NAO");
    	
    	try {
			dto.setDataPagamento(Funcao.localDateToString(lancamento.getDataPagamento()));
		} catch (Exception e) {
			dto.setDataPagamento(null);
		}
    	
    	return dto;
    }
    
    private Lancamento builderLancamento(LancamentoDTO dto) {
    	Lancamento lanc = new Lancamento();
    	lanc.setId(dto.getId());
    	lanc.setConta(dto.getConta());
    	lanc.setFaturaCartao(dto.getFaturaCartao());
    	lanc.setSubCategoria(dto.getSubCategoria());
    	lanc.setDescricao(dto.getDescricao());
    	lanc.setTipo(dto.getTipo());
    	lanc.setValor(dto.getValor());
    	lanc.setData(Funcao.strToLocalDate(dto.getData()));
    	
    	if ("SIM".equals(dto.getPago())) {
    		lanc.setPago(true);
    		lanc.setDataPagamento(LocalDate.now());
    	} else {
    		lanc.setPago(false);
    		lanc.setDataPagamento(null);
    	}
    	
    	return lanc;
    }
    
    public Lancamento gerarLancamentoDaTransferencia(Transferencia transferencia, Tipo tipo) {
    	Lancamento lanc = new Lancamento();
    	
    	lanc.setValor(transferencia.getValor());
    	lanc.setData(transferencia.getData());
    	lanc.setDataPagamento(transferencia.getData());
    	
    	Conta conta = null;
    	if (tipo.equals(Tipo.DESPESA)) {
    		conta = contaService.getPorId(transferencia.getConta().getId()).orElse(null);
    		lanc.setDescricao(transferencia.getDescricao() + " - para conta " + conta.getNome());
    		lanc.setConta(conta);
    	} else {
    		conta = contaService.getPorId(transferencia.getContaDestino().getId()).orElse(null);
    		lanc.setDescricao(transferencia.getDescricao() + " - da conta " + conta.getNome());
    		lanc.setConta(conta);
    	}
    	
    	lanc.setTipo(tipo.toString());
    	lanc.setPago(true);
    	lanc.setTransferencia(transferencia);
    	lanc.setSubCategoria(subCategoriaService.getSubCategoriaTransferencia());

    	salvar(lanc);
    	
    	return lanc;
    }
}
