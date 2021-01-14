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
    private LancamentoRepository repository;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private SubCategoriaService subCategoriaService;

    public Lancamento salvar(Lancamento lancamento) {
    	if (lancamento.getPago()) {
    		lancamento.setDataPagamento(LocalDate.now());
    	}
    	
        return repository.save(lancamento);
    }
    
    public Optional<LancamentoDTO> getPorId(Integer id) {
    	
    	Lancamento lancamento = repository.findById(id).orElse(null);
    	LancamentoDTO dto = builderLancamentoDTO(lancamento);
		return Optional.ofNullable(dto);
    }
    
    public Lancamento getLancamentoPorId(Integer id) {
    	return repository.getOne(id);
    }

    public List<Lancamento> obterTodos() {
		return repository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Integer id) throws Exception {
    	
    	Lancamento lancamento = getLancamentoPorId(id);
    	
    	if (lancamento.getTransferencia() != null)
    		throw new Exception("Lançamento não pode ser deletado, está vinculado a transferência " + lancamento.getTransferencia().getId()); 
    	
    	repository.deleteById(id);
    }
    
    public LancamentoDTO atualizar(LancamentoDTO lancamentoDTO) {
    	
    	Lancamento lanc = repository.save(builderLancamento(lancamentoDTO));
    	
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

    	return lanc;
    }
    
    public Lancamento getPorTransferenciaETipo(Integer idTransferencia, String tipo) {
    	return repository.findByTransferenciaAndTipo(idTransferencia, tipo);
    }
    
    public void removeLancamentoTransferencia(Integer idTransferencia) {
    	List<Lancamento> lancamentos =  repository.findAllByTransferenciaId(idTransferencia);
    	
    	for (Lancamento lancamento : lancamentos) {
    		repository.delete(lancamento);
		}
    }
    
}
