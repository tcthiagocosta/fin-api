package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.Categoria;
import com.meudinheiro.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    public Optional<Categoria> getPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> obterTodos() {
		return categoriaRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(Categoria categoria) {
    	categoriaRepository.delete(categoria);
    }
    
    public Categoria atualizar(Categoria categoria) {
    	return categoriaRepository.save(categoria);
    }
}
