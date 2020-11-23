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

import com.meudinheiro.model.Conta;
import com.meudinheiro.service.ContaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/conta")
@RequiredArgsConstructor
public class ContaController {
	
	private final ContaService contaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta salvar(@RequestBody @Valid Conta conta) {
		try {
			return contaService.salvar(conta);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<Conta> obterTodos() {
        return contaService.obterTodos();
    }
	
	@GetMapping("{id}")
    public Conta acharPorId(@PathVariable Integer id) {
        return contaService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		contaService.getPorId(id)
		.map(conta -> {
			contaService.deletar(conta);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Conta contaAtualizada ) {

    	contaService.getPorId(id)
                .map( conta -> {
                	contaAtualizada.setId(conta.getId());
                    return contaService.atualizar(contaAtualizada);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada."));
    }

}
