package br.com.rv.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="usuario")
public class Usuario implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Long id;
	
	@NotBlank
	@Size(min=2, max=50)
	private String nome;
	
	@NotBlank
	@Size(min=8, max=100)
	@Email(message="Insira um email válido!")
	@Column(unique=true)
	private String username;
	
	@NotBlank
    @Size(min=6, message="A senha deve ter no mínimo de 6 caracteres")
	private String password;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Produto> produtos;

	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Usuario(Long id, @NotBlank @Size(min = 2, max = 50) String nome,
			@NotBlank @Size(min = 2, max = 80) @Email(message = "Insira um email válido!") String username,
			@NotNull String password, List<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", username=" + username + ", password=" + password
				+ ", produtos=" + produtos + "]";
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
