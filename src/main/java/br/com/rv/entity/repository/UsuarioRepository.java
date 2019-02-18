package br.com.rv.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rv.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmailAndSenha(String email, String senha);

	Optional<Usuario> findByIdAndProdutoId(Long usuarioId, Long produtoId);
}
