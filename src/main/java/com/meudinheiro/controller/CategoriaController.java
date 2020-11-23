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

import com.meudinheiro.model.Categoria;
import com.meudinheiro.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {
	
	private final CategoriaService categoriaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria salvar(@RequestBody @Valid Categoria categoria) {
		try {
			return categoriaService.salvar(categoria);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<Categoria> obterTodos() {
        return categoriaService.obterTodos();
    }
	
	@GetMapping("{id}")
    public Categoria acharPorId(@PathVariable Integer id) {
        return categoriaService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		categoriaService.getPorId(id)
		.map(categoria -> {
			categoriaService.deletar(categoria);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Categoria categoriaAtualizada ) {

    	categoriaService.getPorId(id)
                .map( categoria -> {
                	categoriaAtualizada.setId(categoria.getId());
                    return categoriaService.atualizar(categoriaAtualizada);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
    }

}
