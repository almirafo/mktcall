package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the list_segmentation database table.
 * 
 */
@Embeddable
public class ListSegmentationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_list")
	private Long idList;

	private String msisdn;

	public ListSegmentationPK() {
	}
	public Long getIdList() {
		return this.idList;
	}
	public void setIdList(Long idList) {
		this.idList = idList;
	}
	public String getMsisdn() {
		return this.msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ListSegmentationPK)) {
			return false;
		}
		ListSegmentationPK castOther = (ListSegmentationPK)other;
		return 
			this.idList.equals(castOther.idList)
			&& this.msisdn.equals(castOther.msisdn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idList.hashCode();
		hash = hash * prime + this.msisdn.hashCode();
		
		return hash;
	}
}