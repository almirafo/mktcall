package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.Index;

/**
 * Entity implementation class for Entity: Lastcallmsisdn
 *
 */
@Entity
@Table(name="LastCallMsisdn")


@NamedQueries({
	@NamedQuery(name = "Lastcallmsisdn.byMsisdn", query = "select s from LastCallMsisdn s where s.msisdn = :msisdn ")
})


public class LastCallMsisdn implements Serializable {

	
	private static final long serialVersionUID = 1L;
	  
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column(name="id_lastcallmsisdn") 
 	  private Long idlastcallmsisdn;
 	  
	  @Index(name = "idx_lastcallmsisdn01")
 	  @Column(name="msisdn", length=15)
 	  private String msisdn;
 	  
 	  @Column(name="id_campanha")
 	  private Long idCampanha;
	  
	  
	  @Column(name="operation_date")
	  private Timestamp operationDate;


	public Long getIdlastcallmsisdn() {
		return idlastcallmsisdn;
	}


	public void setIdlastcallmsisdn(Long idlastcallmsisdn) {
		this.idlastcallmsisdn = idlastcallmsisdn;
	}


	public String getMsisdn() {
		return msisdn;
	}


	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}


	public Long getIdCampanha() {
		return idCampanha;
	}


	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}


	public Timestamp getOperationDate() {
		return operationDate;
	}


	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}
	

   
}
