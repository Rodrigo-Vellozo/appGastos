package br.com.rv.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rv.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsernameAndPassword(String username, String password);
	
	Usuario findByUsername(String username);
	
	//select * from produto p inner join usuario u on p.id_usuario_fk = u.usuario_id where p.id_usuario_fk =1;
	
	
	
}
