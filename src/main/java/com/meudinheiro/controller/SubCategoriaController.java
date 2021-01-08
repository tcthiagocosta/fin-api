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

import com.meudinheiro.model.SubCategoria;
import com.meudinheiro.service.SubCategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subCategoria")
@RequiredArgsConstructor
public class SubCategoriaController {
	
	private final SubCategoriaService subCategoriaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SubCategoria salvar(@RequestBody @Valid SubCategoria subCategoria) {
		try {
			return subCategoriaService.salvar(subCategoria);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<SubCategoria> obterTodos() {
        return subCategoriaService.obterTodos();
    }
	
	@GetMapping("{id}")
    public SubCategoria acharPorId(@PathVariable Integer id) {
        return subCategoriaService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategoria não encontrada."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		subCategoriaService.getPorId(id)
		.map(subCategoria -> {
			subCategoriaService.deletar(subCategoria);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategoria não encontrada."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid SubCategoria subCategoriaAtualizada ) {

    	subCategoriaService.getPorId(id)
                .map( subCategoria -> {
                	subCategoriaAtualizada.setId(subCategoria.getId());
                    return subCategoriaService.atualizar(subCategoriaAtualizada);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategoria não encontrada."));
    }
    
    @GetMapping("/categoria/{id}")
    public List<SubCategoria> obterTodosPorIdCategoria(@PathVariable Integer id) {
        return subCategoriaService.obterTodosPorIdCategoria(id);
    }

}
