package br.com.rv.entity.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rv.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findProdutoByUsuarioId(Long usuarioId);
	
	Page<Produto> findProdutoByUsuarioId(Long usuarioId, Pageable pageable);
	
	List<Produto> findProdutoByCategoriaAndUsuarioId(String categoria, Long usuarioId);
	
	@Query(value="SELECT p.categoria, MONTHNAME(p.dataCadastro) AS mes, SUM(p.preco) FROM Produto p JOIN p.usuario u "
				 + "WHERE u.id= :id AND p.categoria = :categoria "
			     + "GROUP BY p.categoria, MONTHNAME(p.dataCadastro), YEAR(p.dataCadastro)")
	String [][] getChartTwo(@Param("id")Long id, @Param("categoria")String categoria);
	
}


