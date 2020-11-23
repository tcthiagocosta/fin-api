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

import com.meudinheiro.model.Movimento;
import com.meudinheiro.service.MovimentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movimento")
@RequiredArgsConstructor
public class MovimentoController {
	
	private final MovimentoService movimentoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Movimento salvar(@RequestBody @Valid Movimento movimento) {
		try {
			return movimentoService.salvar(movimento);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<Movimento> obterTodos() {
        return movimentoService.obterTodos();
    }
	
	@GetMapping("{id}")
    public Movimento acharPorId(@PathVariable Integer id) {
        return movimentoService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento não encontrado."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		movimentoService.getPorId(id)
		.map(movimento -> {
			movimentoService.deletar(movimento);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento não encontrado."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Movimento movimentoAtualizado ) {

    	movimentoService.getPorId(id)
                .map( movimento -> {
                	movimentoAtualizado.setId(movimento.getId());
                    return movimentoService.atualizar(movimentoAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento não encontrado."));
    }

}
