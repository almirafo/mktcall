package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Index;


/**
 * The persistent class for the insertion database table.
 * 
 */
@Entity
@Table(name="insertion")
	@NamedQueries({ 
		@NamedQuery(name = "Insertion.All", query = "select i from Insertion i"),
		@NamedQuery(name = "Insertion.Id",  query = "select i from Insertion i where i.idInsertion = :idInsertion"),
		@NamedQuery(name = "Insertion.Campanha", query = "select i from Insertion i where i.campanhaInsertion.idCampanha = :idCampanha"),
		@NamedQuery(name= "Insertion.CampanhaRemove", query = "delete from Insertion as i where i.campanhaInsertion.idCampanha = :idCampanha ")
})
public class Insertion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_insertion", unique=true, nullable=false)
	@Index(name="idx_insertion_id")
	private Long idInsertion;

	@Index(name = "idx_insertion_day_of_week")
	@Column(name="day_of_week", nullable=false)
	private Integer dayOfWeek;

	@Index(name = "idx_insertion_end_time")
	@Column(name="end_time")
	private Timestamp endTime;

	@Index(name = "idx_insertion_start_time")
	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to Campanha
	@ManyToOne
	@JoinColumn(name="id_campanha", nullable=false)
	private Campanha campanhaInsertion;

	public Insertion() {
	}

	public Long getIdInsertion() {
		return this.idInsertion;
	}

	public void setIdInsertion(Long idInsertion) {
		this.idInsertion = idInsertion;
	}

	public Integer getDayOfWeek() {
		return this.dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Campanha getCampanhaInsertion() {
		return campanhaInsertion;
	}

	public void setCampanhaInsertion(Campanha campanhaInsertion) {
		this.campanhaInsertion = campanhaInsertion;
	}



}