package br.com.rv.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=2, max=50)
	private String nome;
	
	@NotBlank
	@Size(min=2, max=50)
	private String categoria;
	
	@NotNull
	private Double preco;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new Date();
	
	@ManyToOne
	@JoinColumn(name="id_usuario_fk")
	private Usuario usuario;
	
	public Produto() {
		
	}

	public Produto(Long id, @NotBlank @Size(min = 2, max = 50) String nome,
			@NotBlank @Size(min = 2, max = 50) String categoria, @NotBlank Double preco, Date dataCadastro,
			Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
		this.dataCadastro = dataCadastro;
		this.usuario = usuario;
	}
	
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", preco=" + preco
				+ ", dataCadastro=" + dataCadastro + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
