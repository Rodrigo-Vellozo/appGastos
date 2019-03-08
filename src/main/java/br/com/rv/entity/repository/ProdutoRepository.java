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
	
//	select categoria, data_cadastro, year(data_cadastro), sum(preco) from produto p 
//		inner join usuario u on p.id_usuario_fk = u.usuario_id
//			where categoria= 'educacao' and month(data_cadastro)=2;
	
	

//	@Query(value="select id, nome, categoria, preco, data_cadastro, year(data_cadastro), sum(preco) from produto p"
//			   + "inner join usuario u on p.id_usuario_fk = u.usuario_id"
//			   + "where categoria= 'alimentacao' and month(data_cadastro)=2",nativeQuery=true)

	@Query(value="SELECT p FROM Produto p JOIN p.usuario u WHERE u.id= :id AND p.categoria = :categoria")
	List<Produto> getInfo(@Param("id")Long id, @Param("categoria")String categoria);
	
	@Query(value="SELECT SUM(p.preco) FROM Produto p JOIN p.usuario u WHERE u.id= :id AND p.categoria = :categoria "
			   + "AND MONTH(p.dataCadastro)=:mes")
	Double getNada(@Param("id")Long id, @Param("categoria")String categoria, @Param("mes")int mes);
	
	@Query(value="SELECT SUM(p.preco), MONTHNAME(p.dataCadastro) FROM Produto p JOIN p.usuario u WHERE u.id= :id AND p.categoria = :categoria "
			   + "AND MONTH(p.dataCadastro)=:mes")
	String getBro(@Param("id")Long id, @Param("categoria")String categoria, @Param("mes")int mes);
	
	
}


