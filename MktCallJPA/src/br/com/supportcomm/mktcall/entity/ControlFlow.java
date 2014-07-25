package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;

import java.sql.Timestamp;


/**
 * The persistent class for the control_flow database table.
 * 
 */
@Entity
@Table(name="control_flow")
@NamedQueries({ 
                @NamedQuery(name = "ControlFlow.ByCallId"    , query = "select c from ControlFlow as c where c.callid = :callId"),
                @NamedQuery(name = "ControlFlow.AllMinutes"  , query = "select c from ControlFlow as c where c.operationDate <= :minutes and c.listenCompleted = false"),
                @NamedQuery(name = "ControlFlow.All"         , query = "select c from ControlFlow as c where c.listenCompleted = false")
    })
public class ControlFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_control")
	private Long idControl;
	
	@IndexColumn(name="callid")
	private String callid;

	@Column(name="id_campanha")
	private Long idCampanha;

	@Column(name="id_cdr")
	private Long idCdr;

	private String msisdn;

	@Column(name="operation_date")
	private Timestamp operationDate;
	
	@Column(name="listen_completed")
	private boolean listenCompleted;
	
	

	public ControlFlow() {
	}

	public Long getIdControl() {
		return this.idControl;
	}

	public void setIdControl(Long idControl) {
		this.idControl = idControl;
	}

	public String getCallid() {
		return this.callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public Long getIdCampanha() {
		return this.idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public Long getIdCdr() {
		return this.idCdr;
	}

	public void setIdCdr(Long idCdr) {
		this.idCdr = idCdr;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public boolean isListenCompleted() {
		return listenCompleted;
	}

	public void setListenCompleted(boolean listenCompleted) {
		this.listenCompleted = listenCompleted;
	}

}