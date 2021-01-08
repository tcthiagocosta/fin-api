package com.meudinheiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meudinheiro.model.SubCategoria;
import com.meudinheiro.repository.SubCategoriaRepository;

@Service
public class SubCategoriaService {
	
	@Autowired
    private SubCategoriaRepository subCategoriaRepository;

    public SubCategoria salvar(SubCategoria subCategoria) {
        return subCategoriaRepository.save(subCategoria);
    }
    
    public Optional<SubCategoria> getPorId(Integer id) {
        return subCategoriaRepository.findById(id);
    }

    public List<SubCategoria> obterTodos() {
		return subCategoriaRepository.findAllByOrderByIdAsc();
	}
    
    public void deletar(SubCategoria subCategoria) {
    	subCategoriaRepository.delete(subCategoria);
    }
    
    public SubCategoria atualizar(SubCategoria subCategoria) {
    	return subCategoriaRepository.save(subCategoria);
    }
    
    public List<SubCategoria> obterTodosPorIdCategoria(Integer id) {
		return subCategoriaRepository.findByCategoriaId(id);
	}
}
