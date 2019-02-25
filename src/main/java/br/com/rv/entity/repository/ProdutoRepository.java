package br.com.rv.entity.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rv.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	
	Page<Produto> findAll(Pageable pageable);
	List<Produto> findProdutoByUsuarioId(Long usuarioId);
	Page<Produto> findProdutoByUsuarioId(Long usuarioId, Pageable pageable);
	
	// Optional<Produto> findByCategoriaAndUsuarioId(String categoria, Long usuarioId); retorna mais de um registro
	//List<Produto> findByCategoriaAndUsuarioId(String categoria, Long usuarioId);
	
	List<Produto> findProdutoByCategoriaAndUsuarioId(String categoria, Long usuarioId);
	
	
	
	
	
}
