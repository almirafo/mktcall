package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;

/**
 * The persistent class for the agência database table.
 * 
 */
@Entity
@Table(name = "agencia")
	@NamedQueries({
		@NamedQuery(name = "Agencia.Anunciante", query = "select a from Agencia a, Anunciante n where n.agencia.idAgencia = a.idAgencia and n.idAnunciante = :idAnunciante "),
		@NamedQuery(name = "Agencia.All", query = "select a from Agencia a"),
		@NamedQuery(name = "Agencia.email", query = "select a from Agencia a where lower(a.email) = :email"),
		@NamedQuery(name = "Agencia.Usuario", query = "select a from Agencia a where a.userAccess.idUserAccess = :idUserAccess"),
		@NamedQuery(name = "Agencia.Id", query = "select a from Agencia a where a.idAgencia = :id"),
		@NamedQuery(name = "Agencia.Campanha", query ="select c from Campanha c, Agencia a where a.idAgencia = :idUserAccess ")
				 })
public class Agencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@IndexColumn(name = "id_agencia")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_agencia", unique=true, nullable=false)
	@Index(name="idx_agencia_id")
	private Long idAgencia;

	@Index(name = "idx_agencia_email")
	@Column(length=255)
	private String email;

	@Index(name = "idx_agencia_nome_contato")
	@Column(name="nome_contato", length=255)
	private String nomeContato;

	@Index(name = "idx_agencia_operation_date")
	@Column(name="operation_date")
	private Timestamp operationDate;

	@Index(name = "idx_agencia_razao_social")
	@Column(name="razao_social", length=255)
	private String razaoSocial;

	@Index(name = "idx_agencia_status")
	private Integer status;

	@Index(name = "idx_agencia_telefone")
	@Column(length=255)
	private String telefone;

	//bi-directional many-to-one association to UserAccess
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_user_access")
	private UserAccess userAccess;

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_campanha")
	private Campanha campanhaAgencia;
	
	@OneToMany(mappedBy = "agencia")
	private List<Anunciante> anunciante;
	
	public Agencia() {
	}

	public Long getIdAgencia() {
		return this.idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
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

	public UserAccess getUserAccess() {
		return this.userAccess;
	}

	public void setUserAccess(UserAccess userAccess) {
		this.userAccess = userAccess;
	}

	public List<Anunciante> getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(List<Anunciante> anunciante) {
		this.anunciante = anunciante;
	}

	public Campanha getCampanhaAgencia() {
		return campanhaAgencia;
	}

	public void setCampanhaAgencia(Campanha campanhaAgencia) {
		this.campanhaAgencia = campanhaAgencia;
	}

}
