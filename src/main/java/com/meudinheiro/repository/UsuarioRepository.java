package com.meudinheiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meudinheiro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String username);

    boolean existsByLogin(String username);
    
    @Query(" select login " +
            "from Usuario " +            
            "order by login ")
    List<String> listarUsuarios();
    
    List<Usuario> findAllByOrderByIdAsc();
}
