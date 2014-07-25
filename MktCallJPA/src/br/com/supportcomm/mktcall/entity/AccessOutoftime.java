package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;

import java.sql.Timestamp;


/**
 * The persistent class for the access_outoftime database table.
 * 
 */
@Entity
@Table(name="access_outoftime")
@NamedQueries({ 
				@NamedQuery(name = "AccessOutoftime.ByMsisdn", query = "select a from AccessOutoftime a where a.msisdn = :msisdn"),
				@NamedQuery(name = "AccessOutoftime.All", query = "select distinct a from AccessOutoftime a")
			  })

public class AccessOutoftime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_list", unique=true, nullable=false)
	@Index(name="idx_accessOutOfTime_id")
	private Long idList;

	@Index(name = "idx_out_of_time_inserted_date")
	@Column(name="inserted_date")
	private Timestamp insertedDate;

	@Index(name = "idx_out_of_time_msisdn")
	@Column(length=15)
	private String msisdn;

	public AccessOutoftime() {
	}

	public Long getIdList() {
		return this.idList;
	}

	public void setIdList(Long idList) {
		this.idList = idList;
	}

	public Timestamp getInsertedDate() {
		return this.insertedDate;
	}

	public void setInsertedDate(Timestamp insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

}

