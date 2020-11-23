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

import com.meudinheiro.model.FaturaCartao;
import com.meudinheiro.service.FaturaCartaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/faturaCartao")
@RequiredArgsConstructor
public class FaturaCartaoController {
	
	private final FaturaCartaoService faturaCartaoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FaturaCartao salvar(@RequestBody @Valid FaturaCartao faturaCartao) {
		try {
			return faturaCartaoService.salvar(faturaCartao);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
    public List<FaturaCartao> obterTodos() {
        return faturaCartaoService.obterTodos();
    }
	
	@GetMapping("{id}")
    public FaturaCartao acharPorId(@PathVariable Integer id) {
        return faturaCartaoService.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fatura não encontrada."));
    }
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		faturaCartaoService.getPorId(id)
		.map(faturaCartao -> {
			faturaCartaoService.deletar(faturaCartao);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fatur não encontrada."));
	}
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid FaturaCartao faturaCartaoAtualizada ) {

    	faturaCartaoService.getPorId(id)
                .map( faturaCartao -> {
                	faturaCartaoAtualizada.setId(faturaCartao.getId());
                    return faturaCartaoService.atualizar(faturaCartaoAtualizada);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fatura não encontrada."));
    }

}
