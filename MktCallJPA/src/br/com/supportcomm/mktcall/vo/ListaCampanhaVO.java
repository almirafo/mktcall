package br.com.supportcomm.mktcall.vo;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 * @generated DT_ID=none
 */
@Entity
public class ListaCampanhaVO
{
	@Id
	 @Column(name="id_campanha")
	private Long idCampanha;

	 @Column(name="call_duration")
	private Integer callDuration;


	private String description;

	 @Column(name="end_datetime")
	private Timestamp endDatetime;

	 @Column(name="insertion_contracted")
	private Long insertionContracted;

	 @Column(name="insertion_reach")
	private Long insertionReach;


	private String name;

	 @Column(name="next_campaign")
	private Boolean nextCampaign;

	 @Column(name="operation_date")
	private Timestamp operationDate;

	private Integer priority;

	 @Column(name="start_datatime")
	private Timestamp startDatatime;


	private Integer status;


	private boolean once;

	@Column(name="id_spot")
	private Long idSpot;
	
	@Column(name="id_list")
	private Long idList;	

	public Long getIdSpot() {
		return idSpot;
	}


	public void setIdSpot(Long idSpot) {
		this.idSpot = idSpot;
	}


	public Long getIdCampanha() {
		return idCampanha;
	}


	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}


	public Integer getCallDuration() {
		return callDuration;
	}


	public void setCallDuration(Integer callDuration) {
		this.callDuration = callDuration;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Timestamp getEndDatetime() {
		return endDatetime;
	}


	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}


	public Long getInsertionContracted() {
		return insertionContracted;
	}


	public void setInsertionContracted(Long insertionContracted) {
		this.insertionContracted = insertionContracted;
	}


	public Long getInsertionReach() {
		return insertionReach;
	}


	public void setInsertionReach(Long insertionReach) {
		this.insertionReach = insertionReach;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Boolean getNextCampaign() {
		return nextCampaign;
	}


	public void setNextCampaign(Boolean nextCampaign) {
		this.nextCampaign = nextCampaign;
	}


	public Timestamp getOperationDate() {
		return operationDate;
	}


	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}


	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public Timestamp getStartDatatime() {
		return startDatatime;
	}


	public void setStartDatatime(Timestamp startDatatime) {
		this.startDatatime = startDatatime;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public boolean isOnce() {
		return once;
	}


	public void setOnce(boolean once) {
		this.once = once;
	}


	public Long getIdList() {
		return idList;
	}

	public void setIdList(Long idList) {
		this.idList = idList;
	}
}
