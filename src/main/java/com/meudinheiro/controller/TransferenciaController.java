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

import com.meudinheiro.model.Transferencia;
import com.meudinheiro.service.TransferenciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transferencia")
@RequiredArgsConstructor
public class TransferenciaController {
	
	private final TransferenciaService transferenciaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transferencia salvar(@RequestBody @Valid Transferencia transferencia) {
		try {
			return transferenciaService.salvar(transferencia);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<Transferencia> obterTodos() {
        return transferenciaService.obterTodos();
    }
	
	@GetMapping("{id}")
    public Transferencia acharPorId(@PathVariable Integer id) {
        return transferenciaService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia não encontrado."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		transferenciaService.getPorId(id)
		.map(transferencia -> {
			transferenciaService.deletar(transferencia);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia não encontrado."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Transferencia transferenciaAtualizado ) {

    	transferenciaService.getPorId(id)
                .map( transferencia -> {
                	transferenciaAtualizado.setId(transferencia.getId());
                    return transferenciaService.atualizar(transferenciaAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia não encontrado."));
    }

}
