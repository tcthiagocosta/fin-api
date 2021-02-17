package com.meudinheiro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meudinheiro.dto.RequestBodyLancamentoFatura;
import com.meudinheiro.model.Cartao;
import com.meudinheiro.model.LancamentoFatura;
import com.meudinheiro.service.LancamentoFaturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lancamentoFatura")
@RequiredArgsConstructor
public class LancamentoFaturaController {
	
	private final LancamentoFaturaService lancamentoFaturaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LancamentoFatura salvar(@RequestBody @Valid RequestBodyLancamentoFatura bodyLancamentoFatura) {
		try {
			return lancamentoFaturaService.salvar(bodyLancamentoFatura.getLancamentoFatura(), bodyLancamentoFatura.getCartao());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<LancamentoFatura> obterTodos() {
        return lancamentoFaturaService.obterTodos();
    }
	
	@GetMapping("{id}")
    public LancamentoFatura acharPorId(@PathVariable Integer id) {
        return lancamentoFaturaService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		lancamentoFaturaService.getPorId(id)
		.map(lancamentoFatura -> {
			lancamentoFaturaService.deletar(lancamentoFatura);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid LancamentoFatura lancamentoFaturaAtualizado ) {

    	lancamentoFaturaService.getPorId(id)
                .map( lancamentoFatura -> {
                	lancamentoFaturaAtualizado.setId(lancamentoFatura.getId());
                    return lancamentoFaturaService.atualizar(lancamentoFaturaAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado."));
    }

}
