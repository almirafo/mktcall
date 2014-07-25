package br.com.supportcomm.mktcall.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "user_history")
@NamedQueries({ 
		@NamedQuery(name = "UserHistory.All", query = "select uh from UserHistory uh"),
		@NamedQuery(name = "UserHistory.idUser", query = "select uh from UserHistory uh where uh.idUserAccess = :idUserAccess"),
		@NamedQuery(name = "UserHistory.byUserIdAndRange", query = "select uh from UserHistory uh where uh.idUserAccess = :idUserAccess and uh.dateInsered >= :startDate and uh.dateInsered <= :endDate"),
		@NamedQuery(name = "UserHistory.byLoginAndRange", query = "select uh from UserHistory uh where uh.userLogin = :login and uh.dateInsered >= :startDate and uh.dateInsered <= :endDate"),
		@NamedQuery(name = "UserHistory.byRange", query = "select uh from UserHistory uh where uh.dateInsered >= :startDate and uh.dateInsered <= :endDate"),
		@NamedQuery(name = "UserHistory.idCampanha", query = "select uh from UserHistory uh where uh.idCampanha  = :idCampanha"),
		@NamedQuery(name = "UserHistory.idSpot", query = "select uh from UserHistory uh where uh.idSpot  = :idSpot")
})
public class UserHistory {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_user_history_id")
	private Long id;

	@Column(name = "id_user_access", nullable = false)
	@Index(name = "idx_user_history_user_access")
	private Long idUserAccess;

	@Column(name = "user_login", nullable = false)
	@Index(name = "idx_user_history_user_login")
	private String userLogin;

	@Column(name = "user_type", nullable = false)
	@Index(name = "idx_user_history_user_type")
	private Long userType;

	@Column(name = "date_insered", nullable = false)
	@Index(name = "idx_user_history_date_insered")
	private Timestamp dateInsered;

	@Column(name = "action", nullable = false)
	@Index(name = "idx_user_history_action")
	private String action;

	@Column(name = "id_campanha", nullable = true)
	@Index(name = "idx_user_history_id_campanha")
	private Long idCampanha;

	@Column(name = "id_spot", nullable = true)
	@Index(name = "idx_user_history_id_spot")
	private Long idSpot;

	public UserHistory() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUserAccess() {
		return idUserAccess;
	}

	public void setIdUserAccess(Long idUserAccess) {
		this.idUserAccess = idUserAccess;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Timestamp getDateInsered() {
		return dateInsered;
	}

	public void setDateInsered(Timestamp dateInsered) {
		this.dateInsered = dateInsered;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public Long getIdSpot() {
		return idSpot;
	}

	public void setIdSpot(Long idSpot) {
		this.idSpot = idSpot;
	}

}
