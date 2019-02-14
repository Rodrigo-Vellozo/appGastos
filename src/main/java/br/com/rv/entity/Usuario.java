package br.com.rv.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Long id;
	
	@NotBlank
	@Size(min=2, max=50)
	private String nome;
	
	@NotBlank
	@Size(min=2, max=80)
	private String email;
	
	@NotBlank
	private String senha;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Produto> produtos;
	
	public Usuario() {
		
	}
	public Usuario(Long id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
