package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the list_detail database table.
 * 
 */
@Entity
@Table(name="list_detail")

@NamedQueries({ 
	

	@NamedQuery(name = "ListDetail.findListDeailBySegmentName", query = "select m from ListDetail m where m.listName = :segmentName"),
	@NamedQuery(name = "ListDetail.All", query = "select m from ListDetail m "),
	@NamedQuery(name = "ListDetail.ById", query = "select m from ListDetail m where m.idList = :idListDetail"),
	@NamedQuery(name = "ListDetail.ByIdCampanha", query = "select ld from ListDetail ld, Campanha c where c.listDetail.idList = ld.idList and c.idCampanha = :idCampanha"),
	@NamedQuery(name = "ListDetail.Processing", query ="select ld from ListDetail ld where ld.listStatus = '2' order by ld.datetimeOperation desc")
})
public class ListDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_list")
	private Long idList;

	@Column(name="datetime_operation")
	private Timestamp datetimeOperation;

	private String filename;

	@Column(name="list_name")
	private String listName;

	@Column(name="list_status")
	private String listStatus;

	@Column(name="list_active")
	private Boolean listActive;
	
	// bi-directional many-to-one association to Campanha
	@OneToMany(mappedBy = "listDetail")
	private List<Campanha> campanhaListDetail;	
	
	public ListDetail() {
	}

	public Long getIdList() {
		return this.idList;
	}

	public void setIdList(Long idList) {
		this.idList = idList;
	}

	public Timestamp getDatetimeOperation() {
		return this.datetimeOperation;
	}

	public void setDatetimeOperation(Timestamp datetimeOperation) {
		this.datetimeOperation = datetimeOperation;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getListName() {
		return this.listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getListStatus() {
		return this.listStatus;
	}

	public void setListStatus(String listStatus) {
		this.listStatus = listStatus;
	}

	public Boolean getListActive() {
		return listActive;
	}

	public void setListActive(Boolean listActive) {
		this.listActive = listActive;
	}

	public List<Campanha> getCampanhaListDetail() {
		return campanhaListDetail;
	}

	public void setCampanhaListDetail(List<Campanha> campanhaListDetail) {
		this.campanhaListDetail = campanhaListDetail;
	}

}