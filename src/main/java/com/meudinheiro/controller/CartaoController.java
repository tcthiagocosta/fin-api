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

import com.meudinheiro.model.Cartao;
import com.meudinheiro.service.CartaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cartao")
@RequiredArgsConstructor
public class CartaoController {
	
	private final CartaoService cartaoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cartao salvar(@RequestBody @Valid Cartao cartao) {
		try {
			return cartaoService.salvar(cartao);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<Cartao> obterTodos() {
        return cartaoService.obterTodos();
    }
	
	@GetMapping("{id}")
    public Cartao acharPorId(@PathVariable Integer id) {
        return cartaoService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao não encontrado."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		cartaoService.getPorId(id)
		.map(cartao -> {
			cartaoService.deletar(cartao);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao não encontrado."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Cartao cartaoAtualizado ) {

    	cartaoService.getPorId(id)
                .map( cartao -> {
                	cartaoAtualizado.setId(cartao.getId());
                    return cartaoService.atualizar(cartaoAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao não encontrado."));
    }

}
