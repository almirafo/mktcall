package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.persistence.*;
import javax.ws.rs.DefaultValue;

import java.sql.Timestamp;


/**
 * The persistent class for the list_segmentation database table.
 * 
 */
@Entity
@Table(name="list_segmentation")


@NamedQueries({ 
	
	@NamedQuery(name = "ListSegmentation.RemoveByListDetail", query = "delete from ListSegmentation m  where m.id.idList = :idListDetail "),
	@NamedQuery(name = "ListSegmentation.All",                query = "select m from ListSegmentation m  where m.id.idList = :idListDetail "),
	@NamedQuery(name = "ListSegmentationFindListDetail.All",  query = "select m from ListSegmentation m  where m.id.msisdn = :msisdn ")
	
})
public class ListSegmentation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ListSegmentationPK id;

	private String custom1;

	private String custom2;
	
	@Column( columnDefinition="default 0")
	private int block;

	@Column(name="datetime_operation")
	private Timestamp datetimeOperation;

	public ListSegmentation() {
		
	}

	public ListSegmentationPK getId() {
		return this.id;
	}

	public void setId(ListSegmentationPK id) {
		this.id = id;
	}

	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	public Timestamp getDatetimeOperation() {
		return this.datetimeOperation;
	}

	public void setDatetimeOperation(Timestamp datetimeOperation) {
		this.datetimeOperation = datetimeOperation;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

}