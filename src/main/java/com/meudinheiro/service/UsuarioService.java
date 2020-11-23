package com.meudinheiro.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meudinheiro.exception.UsuarioCadastradoException;
import com.meudinheiro.model.Usuario;
import com.meudinheiro.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {

        boolean exists = usuarioRepository.existsByLogin(usuario.getLogin());

        if (exists) {
            throw new UsuarioCadastradoException(usuario.getLogin());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));       
        
        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().split(","))
                .build()
                ;
    }

	public List<String> listarNomeUsuarios() {
		return usuarioRepository.listarUsuarios();		
	}
	
	public List<String> listarRolesUsuario(String username) {
		Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));		
		
		return Arrays.asList(usuario.getRoles().split(","));				
	}
	
	public void alteraRolesUsuario(String username, String roles) {
		Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
		
		usuario.setRoles(roles);
		
		usuarioRepository.save(usuario);
	}

	public List<Usuario> obterTodos() {
		return usuarioRepository.findAllByOrderByIdAsc();
	}
	
    public Optional<Usuario> getPorId(Integer id) {
        return usuarioRepository
                .findById(id);
                
    }
    
    public void deletar(Usuario usuario) {
    	usuarioRepository.delete(usuario);
    }
    
    public Usuario atualizar(Usuario usuario) {
    	return usuarioRepository.save(usuario);
    }
	
}
