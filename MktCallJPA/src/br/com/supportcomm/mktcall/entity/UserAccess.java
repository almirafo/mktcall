package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.util.List;

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


/**
 * The persistent class for the user_access database table.
 * 
 */
@Entity
@Table(name="user_access")
@NamedQueries({
	@NamedQuery(name = "UserAccess.Login", query = "select u from UserAccess u where u.login = :login"),
    @NamedQuery(name = "UserAccess.LoginPass", query = "select u from UserAccess u where u.login = :login and u.password = :senha"),
    @NamedQuery(name = "UserAccess.Usuario", query = "select u from UserAccess u where u.idUserAccess = :idUsuario"),
    @NamedQuery(name = "UserAccess.All", query = "select u from UserAccess u"),
    @NamedQuery(name = "UserAccess.ById", query = "select u from UserAccess u where u.idUserAccess = :id")
			  })

public class UserAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user_access", unique=true, nullable=false)
	@Index(name="idx_user_access_id")
	private Long idUserAccess;

	@Index(name = "idx_useraccess_login")
	@Column(length=30, unique=true, nullable=false )
	private String login;

	@Index(name = "idx_useraccess_password")
	@Column(length=32,nullable=false)
	private String password;

	@Index(name = "idx_useraccess_status")
	@Column(nullable=false)
	private Integer status;
	
	@Index(name = "idx_useraccess_email")
	@Column(length=255,name="email")
	private String email;

	@ManyToOne
	@JoinColumn(name="id_user_type")
	private UserType userType;
	
	@OneToMany(mappedBy = "userAccess")
	private List<Campanha> campanha;

	public UserAccess() {
	}

	public Long getIdUserAccess() {
		return this.idUserAccess;
	}

	public void setIdUserAccess(Long idUserAccess) {
		this.idUserAccess = idUserAccess;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Campanha> getCampanha() {
		return campanha;
	}

	public void setCampanha(List<Campanha> campanha) {
		this.campanha = campanha;
	}


}