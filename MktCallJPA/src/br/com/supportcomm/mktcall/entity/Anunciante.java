package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the anunciante database table.
 * 
 */
@Entity
@Table(name = "anunciante")
@NamedQueries({ @NamedQuery(name = "Anunciante.Agencia", query = "select a from Anunciante a where a.agencia.idAgencia = :idAgencia"),
	    @NamedQuery(name = "Anunciante.email", query = "select a from Anunciante a where lower(a.email) = :email"),
		@NamedQuery(name = "Anunciante.UserAccessAgencia", query = "select a from Anunciante a where a.userAccess.idUserAccess = :idUserAccess"),
		@NamedQuery(name = "Anunciante.All", query = "select a from Anunciante a"), @NamedQuery(name = "Anunciante.Id", query = "select a from Anunciante a where a.idAnunciante = :id") })
public class Anunciante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_anunciante", unique = true, nullable = false)
	@Index(name="idx_anunciante_id")
	private Long idAnunciante;

	@Index(name = "idx_anunciante_email")
	@Column(length = 255)
	private String email;

	@Index(name = "idx_anunciante_nome_contato")
	@Column(name = "nome_contato", length = 255)
	private String nomeContato;

	@Index(name = "idx_anunciante_operation_date")
	@Column(name = "operation_date")
	private Timestamp operationDate;

	@Index(name = "idx_anunciante_razao_social")
	@Column(name = "razao_social", length = 255)
	private String razaoSocial;

	@Index(name = "idx_anunciante_status")
	private Integer status;

	@Index(name = "idx_anunciante_telefone")
	@Column(length = 255)
	private String telefone;

	// bi-directional many-to-one association to Agencia
	@ManyToOne
	@JoinColumn(name = "id_agencia", nullable = false)
	private Agencia agencia;

	// bi-directional many-to-one association to UserAccess
	@ManyToOne
	@JoinColumn(name = "id_user_access", nullable = false)
	private UserAccess userAccess;

	// bi-directional many-to-one association to Spot
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "anunciante", cascade = CascadeType.ALL)
	private List<Spot> spots;

	public Anunciante() {
	}

	public Long getIdAnunciante() {
		return this.idAnunciante;
	}

	public void setIdAnunciante(Long idAnunciante) {
		this.idAnunciante = idAnunciante;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeContato() {
		return this.nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Agencia getAgencia() {
		return this.agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public UserAccess getUserAccess() {
		return this.userAccess;
	}

	public void setUserAccess(UserAccess userAccess) {
		this.userAccess = userAccess;
	}

	public List<Spot> getSpots() {
		return this.spots;
	}

	public void setSpots(List<Spot> spots) {
		this.spots = spots;
	}

}