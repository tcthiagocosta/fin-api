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

import com.meudinheiro.model.Lancamento;
import com.meudinheiro.service.LancamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lancamento")
@RequiredArgsConstructor
public class LancamentoController {
	
	private final LancamentoService lancamentoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Lancamento salvar(@RequestBody @Valid Lancamento lancamento) {
		try {
			return lancamentoService.salvar(lancamento);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<Lancamento> obterTodos() {
        return lancamentoService.obterTodos();
    }
	
	@GetMapping("{id}")
    public Lancamento acharPorId(@PathVariable Integer id) {
        return lancamentoService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		lancamentoService.getPorId(id)
		.map(lancamento -> {
			lancamentoService.deletar(lancamento);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Lancamento lancamentoAtualizado ) {

    	lancamentoService.getPorId(id)
                .map( lancamento -> {
                	lancamentoAtualizado.setId(lancamento.getId());
                    return lancamentoService.atualizar(lancamentoAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado."));
    }

}
