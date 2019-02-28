package br.com.rv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.rv.entity.Usuario;
import br.com.rv.entity.repository.UsuarioRepository;

@Repository
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository uRepository;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = uRepository.findByUsername(username);
		if (usuario==null) {
			throw new UsernameNotFoundException("Email "+username+" não encontrado.");
		}
		return usuario;
	}

}
