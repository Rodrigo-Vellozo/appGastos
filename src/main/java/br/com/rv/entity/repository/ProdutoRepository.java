package br.com.rv.entity.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rv.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findProdutoByUsuarioId(Long usuarioId);
	Page<Produto> findProdutoByUsuarioId(Long usuarioId, Pageable pageable);
	
	List<Produto> findProdutoByCategoriaAndUsuarioId(String categoria, Long usuarioId);

//	select categoria, data_cadastro, year(data_cadastro), sum(preco) from produto p 
//		inner join usuario u on p.id_usuario_fk = u.usuario_id
//			where categoria= 'educacao' and month(data_cadastro)=2;
	
}
